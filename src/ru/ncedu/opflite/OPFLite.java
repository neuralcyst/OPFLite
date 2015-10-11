package ru.ncedu.opflite;

import ru.ncedu.opflite.functionaltests.CustomerAccountTests;
import ru.ncedu.opflite.functionaltests.ServicesTest;

public class OPFLite {

    public static void main(String[] args) {
	    System.out.println("RUN TESTS");
        CustomerAccountTests.runTests();
        ServicesTest.runTests();
        System.out.println("COMPLETED SUCCESSFULLY");
    }
}
