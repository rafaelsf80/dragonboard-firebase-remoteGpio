/**
 * Copyright 2016 Rafael Sanchez Fuentes
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
 */

package es.rafaelsf80.apps.db410c.remotegpio;

public class Gpio {
    private int pin23, pin24, pin25, pin26, pin27, pin28, pin29, pin30, pin31, pin32, pin33, pin34;

    public Gpio() {
    }

    public Gpio(int pin23, int pin24, int pin25, int pin26, int pin27, int pin28, int pin29, int pin30, int pin31, int pin32, int pin33, int pin34) {
        this.pin23 = pin23;
        this.pin24 = pin24;
        this.pin25 = pin25;
        this.pin26 = pin26;
        this.pin27 = pin27;
        this.pin28 = pin28;
        this.pin29 = pin29;
        this.pin30 = pin30;
        this.pin31 = pin31;
        this.pin32 = pin32;
        this.pin33 = pin33;
        this.pin34 = pin34;
    }

    public int getPin23() {
        return pin23;
    }

    public int getPin24() {
        return pin24;
    }

    public int getPin25() {
        return pin25;
    }

    public int getPin26() {
        return pin26;
    }

    public int getPin27() {
        return pin27;
    }

    public int getPin28() {
        return pin28;
    }

    public int getPin29() {
        return pin29;
    }

    public int getPin30() {
        return pin30;
    }

    public int getPin31() {
        return pin31;
    }

    public int getPin32() {
        return pin32;
    }

    public int getPin33() {
        return pin33;
    }

    public int getPin34() {
        return pin34;
    }

    public void setPin23(int pin23) {
        this.pin23 = pin23;
    }

    public void setPin24(int pin24) {
        this.pin24 = pin24;
    }

    public void setPin25(int pin25) {
        this.pin25 = pin25;
    }

    public void setPin26(int pin26) {
        this.pin26 = pin26;
    }

    public void setPin27(int pin27) {
        this.pin27 = pin27;
    }

    public void setPin28(int pin28) {
        this.pin28 = pin28;
    }

    public void setPin29(int pin29) {
        this.pin29 = pin29;
    }

    public void setPin30(int pin30) {
        this.pin30 = pin30;
    }

    public void setPin31(int pin31) {
        this.pin31 = pin31;
    }

    public void setPin32(int pin32) {
        this.pin32 = pin32;
    }

    public void setPin33(int pin33) {
        this.pin33 = pin33;
    }

    public void setPin34(int pin34) {
        this.pin34 = pin34;
    }
}

