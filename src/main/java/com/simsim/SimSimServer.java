package com.simsim;

public class SimSimServer {

    public static void main(String[] args) {
        String argJsonString = args[0];
        String jsonString = "{\n" +
                "\t\"name\": \"John\",\n" +
                "\t\"age\": 30,\n" +
                "\t\"married\": true,\n" +
                "\t\"retired\": null,\n" +
                "\t\"children\": [\"Ann\", \"Billy\"],\n" +
                "\t\"dogs\": null,\n" +
                "\t\"cats\": 0,\n" +
                "\t\"cars\": [{\n" +
                "\t\t\t\"model\": \"BMW 230\",\n" +
                "\t\t\t\"mpg\": 27.5,\n" +
                "\t\t\t\"vin\": \"\",\n" +
                "\t\t\t\"manual\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"model\": \"Ford Edge\",\n" +
                "\t\t\t\"mpg\": 24.1,\n" +
                "\t\t\t\"features\": []\n" +
                "\t\t},\n" +
                "\t\t[null, \"\"],\n" +
                "\t\tfalse,\n" +
                "\t\tnull\n" +
                "\t]\n" +
                "}";
        jsonString = "[{}, null, [[[]]]]";
        jsonString = "null";
        SimSimService simSimService = new SimSimService();
        System.out.println(simSimService.removeElements(argJsonString));
    }
}
