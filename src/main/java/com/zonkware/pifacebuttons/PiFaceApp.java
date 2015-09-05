package com.zonkware.pifacebuttons;

import com.pi4j.component.switches.SwitchListener;
import com.pi4j.component.switches.SwitchState;
import com.pi4j.component.switches.SwitchStateChangeEvent;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.PiFaceLed;
import com.pi4j.device.piface.PiFaceSwitch;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

import java.io.IOException;

public class PiFaceApp {

    private static PiFace piface;

    public static void main(String[] args) {
        try {
            piface = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
        } catch (IOException e) {
            piface = null;
            e.printStackTrace();
        }

        if (piface != null) {

            piface.getSwitch(PiFaceSwitch.S1).addListener(new SwitchListener() {

                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if (switchStateChangeEvent.getNewState() == SwitchState.ON) {
                        piface.getLed(PiFaceLed.LED0).on();
                    } else {
                        piface.getLed(PiFaceLed.LED0).off();
                    }
                }

            });

            piface.getSwitch(PiFaceSwitch.S2).addListener(new SwitchListener() {

                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if (switchStateChangeEvent.getNewState() == SwitchState.ON) {
                        piface.getLed(PiFaceLed.LED1).blink(200L);
                    } else {
                        piface.getLed(PiFaceLed.LED1).blink(0);
                        piface.getLed(PiFaceLed.LED1).off();
                    }
                }

            });

        }

    }
}
