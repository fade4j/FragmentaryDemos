package com.spacecowboy.fragmentarydemos.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author: SpaceCowboy
 * @date: 2017/12/2
 * @description:
 */

public class Rectangle {

    private final String CODE_VERTEX_SHADER = "attribute vec3 aPosition;"
            + "void main(){"
            + "gl_Position = vec4(aPosition,1);"
            + "}";

    private final String CODE_FRAGMENT_SHADER = "precision mediump float;"
            + "void main(){"
            + "gl_FragColor = vec4(1,0,0,0);"
            + "}";

    private final String CODE_VERTEX_SHADER_WITH_PROJECTIION = "uniform mat4 uProjectionMatrix;" // 增加这一行
            + "attribute vec3 aPosition;"
            + "void main(){"
            + "gl_Position = uProjectionMatrix * vec4(aPosition,1);" // 不是直接赋值而是乘以投影矩阵
            + "}";

    private int aPosition;
    private int glProgram;
    private FloatBuffer mVertexBuffer;

    public Rectangle() {
        initData();
    }

    private void initData() {
        /*
        (float) -0.5, (float) -0.5, 0,
            (float) 0.5, (float) -0.5, 0,
            (float) -0.5, (float) 0.5, 0,
            (float) 0.5, (float) 0.5, 0
        */
        float[] vertexArray = new float[]{
                -0.5f, 0.5f, 0,
                0.5f, 0.5f, 0,
                0.5f, -0.5f, 0,
                -0.5f, -0.5f, 0
        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertexArray.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = buffer.asFloatBuffer();
        mVertexBuffer.put(vertexArray);
        mVertexBuffer.position(0);

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, CODE_VERTEX_SHADER_WITH_PROJECTIION);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, CODE_FRAGMENT_SHADER);

        glProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glProgram, vertexShader);
        GLES20.glAttachShader(glProgram, fragmentShader);
        GLES20.glLinkProgram(glProgram);

        aPosition = GLES20.glGetAttribLocation(glProgram, "aPosition");
    }

    public void draw() {
        GLES20.glUseProgram(glProgram);
        GLES20.glVertexAttribPointer(aPosition, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(aPosition);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }

    private int loadShader(int glShaderType, String shaderCode) {
        int shader = GLES20.glCreateShader(glShaderType);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
