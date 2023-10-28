package raf.classycraft;

import raf.classycraft.app.core.ApplicationFramework;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        appCore.initialize();

    }
}