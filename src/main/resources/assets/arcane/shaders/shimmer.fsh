/**
* Code by https://github.com/liixini, adapted by PadjokeJ
*
* MIT License:
* Copyright (c) 2026 liixini
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/

#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

#moj_import <minecraft:globals.glsl>

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform ShimmerConfig {
    float DisplaceStrength;
};

out vec4 fragColor;

// Wizardry I found online at https://github.com/liixini/shaders/blob/main/smoke/open.glsl

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));
    return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
}

float fbm(vec2 p) {
    float v = 0.0;
    float amp = 0.5;
    for (int i = 0; i < 6; i++) {
        v += amp * noise(p);
        p *= 2.0;
        amp *= 0.5;
    }
    return v;
}

float warpedFbm(vec2 p, float t) {
    vec2 q = vec2(fbm(p + vec2(0.0, 0.0)),
            fbm(p + vec2(5.2, 1.3)));

    vec2 r = vec2(fbm(p + 6.0 * q + vec2(1.7, 9.2) + 0.25 * t),
            fbm(p + 6.0 * q + vec2(8.3, 2.8) + 0.22 * t));

    vec2 s = vec2(fbm(p + 5.0 * r + vec2(3.1, 7.4) + 0.18 * t),
            fbm(p + 5.0 * r + vec2(6.7, 0.9) + 0.2 * t));

    return fbm(p + 6.0 * s);
}

void main(){
    vec4 shimmerColor = vec4(0.765, 0.071, 0.959, 1.0);
    vec2 sizeRatio = OutSize / InSize;

    float p = GameTime * 250.0;

    vec2 uv = texCoord;

    float t = p * 112.0;

    float smokeSeed = 0.1235;

    float fluid = warpedFbm(uv * 2.0 + smokeSeed, p + smokeSeed);

    vec2 center = uv - 0.5;
    float dist = length(center * vec2(1.0, 1.0));

    float appear = (1.0 - dist * 1.2) + (1.0 - fluid) * 0.7;
    float edgeStrength = smoothstep(appear + 0.5, appear - 0.5, 0.5);

    vec2 wq = vec2(fbm(uv * 2.0),
            fbm(uv * 2.0 + vec2(5.2, t * 0.2)));
    vec2 wr = vec2(fbm(uv * 2.0 + 4.0 * wq + vec2(1.7, 9.2)),
            fbm(uv * 2.0 + 4.0 * wq + vec2(8.3, 2.8)));
    vec2 warpedUV = uv + DisplaceStrength * 0.01 * (wr - 0.5);

    vec2 tex_coords = texCoord * warpedUV;

    vec4 color = texture(InSampler, warpedUV);
    vec4 outColor = mix(shimmerColor, color, edgeStrength);

    fragColor = vec4(outColor.rgb, 1.0);
}
