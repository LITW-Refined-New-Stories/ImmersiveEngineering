package blusunrize.immersiveengineering.common.util.chickenbones;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.obj.Vertex;

/**
 * Copyright (C) 2013 Chicken-Bones
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
// This file originally copied from CodeChickenLib, courtesy of Chicken-Bones.
// This is a customized version created by Steven Blom (AtomicBlom) of the Steam and Steel development team.
// Slight adjustments have been made to fit my code.

public class Matrix4 {

    // m<row><column>
    public double m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;

    public Matrix4() {
        m00 = m11 = m22 = m33 = 1;
    }

    public Matrix4(double d00, double d01, double d02, double d03, double d10, double d11, double d12, double d13,
        double d20, double d21, double d22, double d23, double d30, double d31, double d32, double d33) {
        m00 = d00;
        m01 = d01;
        m02 = d02;
        m03 = d03;
        m10 = d10;
        m11 = d11;
        m12 = d12;
        m13 = d13;
        m20 = d20;
        m21 = d21;
        m22 = d22;
        m23 = d23;
        m30 = d30;
        m31 = d31;
        m32 = d32;
        m33 = d33;
    }

    public Matrix4(Matrix4 mat) {
        set(mat);
    }

    public Matrix4 setIdentity() {
        m00 = m11 = m22 = m33 = 1;
        m01 = m02 = m03 = m10 = m12 = m13 = m20 = m21 = m23 = m30 = m31 = m32 = 0;
        return this;
    }

    public Matrix4 translate(Vertex vec) {
        m03 += m00 * vec.x + m01 * vec.y + m02 * vec.z;
        m13 += m10 * vec.x + m11 * vec.y + m12 * vec.z;
        m23 += m20 * vec.x + m21 * vec.y + m22 * vec.z;
        m33 += m30 * vec.x + m31 * vec.y + m32 * vec.z;
        return this;
    }

    public Matrix4 translate(double x, double y, double z) {
        m03 += m00 * x + m01 * y + m02 * z;
        m13 += m10 * x + m11 * y + m12 * z;
        m23 += m20 * x + m21 * y + m22 * z;
        m33 += m30 * x + m31 * y + m32 * z;
        return this;
    }

    public Matrix4 scale(Vertex vec) {
        m00 *= vec.x;
        m10 *= vec.x;
        m20 *= vec.x;
        m30 *= vec.x;
        m01 *= vec.y;
        m11 *= vec.y;
        m21 *= vec.y;
        m31 *= vec.y;
        m02 *= vec.z;
        m12 *= vec.z;
        m22 *= vec.z;
        m32 *= vec.z;
        return this;
    }

    public Matrix4 rotate(double angle, Vertex axis) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double mc = 1.0f - c;
        double xy = axis.x * axis.y;
        double yz = axis.y * axis.z;
        double xz = axis.x * axis.z;
        double xs = axis.x * s;
        double ys = axis.y * s;
        double zs = axis.z * s;
        double f00 = axis.x * axis.x * mc + c;
        double f10 = xy * mc + zs;
        double f20 = xz * mc - ys;
        double f01 = xy * mc - zs;
        double f11 = axis.y * axis.y * mc + c;
        double f21 = yz * mc + xs;
        double f02 = xz * mc + ys;
        double f12 = yz * mc - xs;
        double f22 = axis.z * axis.z * mc + c;
        double t00 = m00 * f00 + m01 * f10 + m02 * f20;
        double t10 = m10 * f00 + m11 * f10 + m12 * f20;
        double t20 = m20 * f00 + m21 * f10 + m22 * f20;
        double t30 = m30 * f00 + m31 * f10 + m32 * f20;
        double t01 = m00 * f01 + m01 * f11 + m02 * f21;
        double t11 = m10 * f01 + m11 * f11 + m12 * f21;
        double t21 = m20 * f01 + m21 * f11 + m22 * f21;
        double t31 = m30 * f01 + m31 * f11 + m32 * f21;
        m02 = m00 * f02 + m01 * f12 + m02 * f22;
        m12 = m10 * f02 + m11 * f12 + m12 * f22;
        m22 = m20 * f02 + m21 * f12 + m22 * f22;
        m32 = m30 * f02 + m31 * f12 + m32 * f22;
        m00 = t00;
        m10 = t10;
        m20 = t20;
        m30 = t30;
        m01 = t01;
        m11 = t11;
        m21 = t21;
        m31 = t31;
        return this;
    }

    public Matrix4 rotate(double angle, double x, double y, double z) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double mc = 1.0f - c;
        double xy = x * y;
        double yz = y * z;
        double xz = x * z;
        double xs = x * s;
        double ys = y * s;
        double zs = z * s;
        double f00 = x * x * mc + c;
        double f10 = xy * mc + zs;
        double f20 = xz * mc - ys;
        double f01 = xy * mc - zs;
        double f11 = y * y * mc + c;
        double f21 = yz * mc + xs;
        double f02 = xz * mc + ys;
        double f12 = yz * mc - xs;
        double f22 = z * z * mc + c;
        double t00 = m00 * f00 + m01 * f10 + m02 * f20;
        double t10 = m10 * f00 + m11 * f10 + m12 * f20;
        double t20 = m20 * f00 + m21 * f10 + m22 * f20;
        double t30 = m30 * f00 + m31 * f10 + m32 * f20;
        double t01 = m00 * f01 + m01 * f11 + m02 * f21;
        double t11 = m10 * f01 + m11 * f11 + m12 * f21;
        double t21 = m20 * f01 + m21 * f11 + m22 * f21;
        double t31 = m30 * f01 + m31 * f11 + m32 * f21;
        m02 = m00 * f02 + m01 * f12 + m02 * f22;
        m12 = m10 * f02 + m11 * f12 + m12 * f22;
        m22 = m20 * f02 + m21 * f12 + m22 * f22;
        m32 = m30 * f02 + m31 * f12 + m32 * f22;
        m00 = t00;
        m10 = t10;
        m20 = t20;
        m30 = t30;
        m01 = t01;
        m11 = t11;
        m21 = t21;
        m31 = t31;
        return this;
    }

    public Matrix4 leftMultiply(Matrix4 mat) {
        double n00 = m00 * mat.m00 + m10 * mat.m01 + m20 * mat.m02 + m30 * mat.m03;
        double n01 = m01 * mat.m00 + m11 * mat.m01 + m21 * mat.m02 + m31 * mat.m03;
        double n02 = m02 * mat.m00 + m12 * mat.m01 + m22 * mat.m02 + m32 * mat.m03;
        double n03 = m03 * mat.m00 + m13 * mat.m01 + m23 * mat.m02 + m33 * mat.m03;
        double n10 = m00 * mat.m10 + m10 * mat.m11 + m20 * mat.m12 + m30 * mat.m13;
        double n11 = m01 * mat.m10 + m11 * mat.m11 + m21 * mat.m12 + m31 * mat.m13;
        double n12 = m02 * mat.m10 + m12 * mat.m11 + m22 * mat.m12 + m32 * mat.m13;
        double n13 = m03 * mat.m10 + m13 * mat.m11 + m23 * mat.m12 + m33 * mat.m13;
        double n20 = m00 * mat.m20 + m10 * mat.m21 + m20 * mat.m22 + m30 * mat.m23;
        double n21 = m01 * mat.m20 + m11 * mat.m21 + m21 * mat.m22 + m31 * mat.m23;
        double n22 = m02 * mat.m20 + m12 * mat.m21 + m22 * mat.m22 + m32 * mat.m23;
        double n23 = m03 * mat.m20 + m13 * mat.m21 + m23 * mat.m22 + m33 * mat.m23;
        double n30 = m00 * mat.m30 + m10 * mat.m31 + m20 * mat.m32 + m30 * mat.m33;
        double n31 = m01 * mat.m30 + m11 * mat.m31 + m21 * mat.m32 + m31 * mat.m33;
        double n32 = m02 * mat.m30 + m12 * mat.m31 + m22 * mat.m32 + m32 * mat.m33;
        double n33 = m03 * mat.m30 + m13 * mat.m31 + m23 * mat.m32 + m33 * mat.m33;
        m00 = n00;
        m01 = n01;
        m02 = n02;
        m03 = n03;
        m10 = n10;
        m11 = n11;
        m12 = n12;
        m13 = n13;
        m20 = n20;
        m21 = n21;
        m22 = n22;
        m23 = n23;
        m30 = n30;
        m31 = n31;
        m32 = n32;
        m33 = n33;
        return this;
    }

    public Matrix4 multiply(Matrix4 mat) {
        double n00 = m00 * mat.m00 + m01 * mat.m10 + m02 * mat.m20 + m03 * mat.m30;
        double n01 = m00 * mat.m01 + m01 * mat.m11 + m02 * mat.m21 + m03 * mat.m31;
        double n02 = m00 * mat.m02 + m01 * mat.m12 + m02 * mat.m22 + m03 * mat.m32;
        double n03 = m00 * mat.m03 + m01 * mat.m13 + m02 * mat.m23 + m03 * mat.m33;
        double n10 = m10 * mat.m00 + m11 * mat.m10 + m12 * mat.m20 + m13 * mat.m30;
        double n11 = m10 * mat.m01 + m11 * mat.m11 + m12 * mat.m21 + m13 * mat.m31;
        double n12 = m10 * mat.m02 + m11 * mat.m12 + m12 * mat.m22 + m13 * mat.m32;
        double n13 = m10 * mat.m03 + m11 * mat.m13 + m12 * mat.m23 + m13 * mat.m33;
        double n20 = m20 * mat.m00 + m21 * mat.m10 + m22 * mat.m20 + m23 * mat.m30;
        double n21 = m20 * mat.m01 + m21 * mat.m11 + m22 * mat.m21 + m23 * mat.m31;
        double n22 = m20 * mat.m02 + m21 * mat.m12 + m22 * mat.m22 + m23 * mat.m32;
        double n23 = m20 * mat.m03 + m21 * mat.m13 + m22 * mat.m23 + m23 * mat.m33;
        double n30 = m30 * mat.m00 + m31 * mat.m10 + m32 * mat.m20 + m33 * mat.m30;
        double n31 = m30 * mat.m01 + m31 * mat.m11 + m32 * mat.m21 + m33 * mat.m31;
        double n32 = m30 * mat.m02 + m31 * mat.m12 + m32 * mat.m22 + m33 * mat.m32;
        double n33 = m30 * mat.m03 + m31 * mat.m13 + m32 * mat.m23 + m33 * mat.m33;
        m00 = n00;
        m01 = n01;
        m02 = n02;
        m03 = n03;
        m10 = n10;
        m11 = n11;
        m12 = n12;
        m13 = n13;
        m20 = n20;
        m21 = n21;
        m22 = n22;
        m23 = n23;
        m30 = n30;
        m31 = n31;
        m32 = n32;
        m33 = n33;
        return this;
    }

    public Matrix4 transpose() {
        double n00 = m00;
        double n10 = m01;
        double n20 = m02;
        double n30 = m03;
        double n01 = m10;
        double n11 = m11;
        double n21 = m12;
        double n31 = m13;
        double n02 = m20;
        double n12 = m21;
        double n22 = m22;
        double n32 = m23;
        double n03 = m30;
        double n13 = m31;
        double n23 = m32;
        double n33 = m33;
        m00 = n00;
        m01 = n01;
        m02 = n02;
        m03 = n03;
        m10 = n10;
        m11 = n11;
        m12 = n12;
        m13 = n13;
        m20 = n20;
        m21 = n21;
        m22 = n22;
        m23 = n23;
        m30 = n30;
        m31 = n31;
        m32 = n32;
        m33 = n33;
        return this;
    }

    public Matrix4 copy() {
        return new Matrix4(this);
    }

    public Matrix4 set(Matrix4 mat) {
        m00 = mat.m00;
        m01 = mat.m01;
        m02 = mat.m02;
        m03 = mat.m03;
        m10 = mat.m10;
        m11 = mat.m11;
        m12 = mat.m12;
        m13 = mat.m13;
        m20 = mat.m20;
        m21 = mat.m21;
        m22 = mat.m22;
        m23 = mat.m23;
        m30 = mat.m30;
        m31 = mat.m31;
        m32 = mat.m32;
        m33 = mat.m33;
        return this;
    }

    public void apply(Matrix4 mat) {
        mat.multiply(this);
    }

    private void mult3x3(Vertex vec) {
        double x = m00 * vec.x + m01 * vec.y + m02 * vec.z;
        double y = m10 * vec.x + m11 * vec.y + m12 * vec.z;
        double z = m20 * vec.x + m21 * vec.y + m22 * vec.z;
        vec.x = (float) x;
        vec.y = (float) y;
        vec.z = (float) z;
    }

    public void apply(Vertex vec) {
        mult3x3(vec);
        vec.x += m03;
        vec.y += m13;
        vec.z += m23;
    }

    private void mult3x3(Vec3 vec) {
        double x = m00 * vec.xCoord + m01 * vec.yCoord + m02 * vec.zCoord;
        double y = m10 * vec.xCoord + m11 * vec.yCoord + m12 * vec.zCoord;
        double z = m20 * vec.xCoord + m21 * vec.yCoord + m22 * vec.zCoord;
        vec.xCoord = (float) x;
        vec.yCoord = (float) y;
        vec.zCoord = (float) z;
    }

    public void apply(Vec3 vec) {
        mult3x3(vec);
        vec.xCoord += m03;
        vec.yCoord += m13;
        vec.zCoord += m23;
    }

    @Override
    public String toString() {
        MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
        return "[" + new BigDecimal(m00, cont)
            + ","
            + new BigDecimal(m01, cont)
            + ","
            + new BigDecimal(m02, cont)
            + ","
            + new BigDecimal(m03, cont)
            + "]\n"
            + "["
            + new BigDecimal(m10, cont)
            + ","
            + new BigDecimal(m11, cont)
            + ","
            + new BigDecimal(m12, cont)
            + ","
            + new BigDecimal(m13, cont)
            + "]\n"
            + "["
            + new BigDecimal(m20, cont)
            + ","
            + new BigDecimal(m21, cont)
            + ","
            + new BigDecimal(m22, cont)
            + ","
            + new BigDecimal(m23, cont)
            + "]\n"
            + "["
            + new BigDecimal(m30, cont)
            + ","
            + new BigDecimal(m31, cont)
            + ","
            + new BigDecimal(m32, cont)
            + ","
            + new BigDecimal(m33, cont)
            + "]";
    }
}
