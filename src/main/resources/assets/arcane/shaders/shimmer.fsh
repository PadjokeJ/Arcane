#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

#moj_import <minecraft:globals.glsl>

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

out vec4 fragColor;

float hash12(vec2 p) {
    vec3 p3 = fract(vec3(p.xyx) * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    vec2 u = f*f*(3.0-2.0*f);

    float a = hash12(i);
    float b = hash12(i + vec2(1.0, 0.0));
    float c = hash12(i + vec2(0.0, 1.0));
    float d = hash12(i + vec2(1.0, 1.0));

    return mix(mix(a,b,u.x), mix(c,d,u.x), u.y);
}

float fbm(vec2 p) {
    float v = 0.0;
    float a = 0.55;
    for (int i = 0; i < 5; i++) {
        v += a * noise(p);
        p *= 2.0;
        a *= 0.55;
    }
    return v;
}

void main(){
    vec4 shimmerColor = vec4(0.765, 0.071, 0.959, 1.0);
    vec2 sizeRatio = OutSize / InSize;

    float time = GameTime * 1000.0;

    vec4 diffuseColor = texture(InSampler, texCoord);

    float d = min(texCoord.x, 1.0 - texCoord.x);

    float band = smoothstep(0.22, 0.0, d);

    vec2 p = texCoord.xy;
    p.x = (texCoord.x - 0.5) * 2.0;

    float flameRise = p.y * 7.0 - time * 2.2;
    float n = fbm(vec2(p.x * 2.8 * time, flameRise + p.x));

    float flame = smoothstep(0.55, 0.06, n + 0.22 * sin(flameRise * 1.9));
    flame *= band;

    float k = pow(flame, 1.0);

    vec4 outColor = mix(diffuseColor, shimmerColor, k);
    fragColor = vec4(outColor.rgb, 1.0);
}
