package my.warehouse.controllers;

public class TestUntil {
    static String getJSONAdmission(String warehouse){
        return "\t{\n" +
                "  \"number\": \"1\",\n" +
                "  \"warehouse\": {\n" +
                "    \"name\": \"" + warehouse + "\"\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "  {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"Холодильник\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    static String getJSONMoving(String nameProduct){
        return "\t{\n" +
                "  \"number\": \"1\",\n" +
                "  \"warehouseOne\": {\n" +
                "    \"name\": \"Склад 1\"\n" +
                "  },\n" +
                "  \"warehouseTwo\": {\n" +
                "    \"name\": \"Склад 2\"\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "  {\n" +
                "    \"article\": \"7A1FCTG44\",\n" +
                "    \"name\": \"" + nameProduct + "\",\n" +
                "    \"priceLastPurchase\": \"50000\",\n" +
                "    \"priceLastSale\": \"50000\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
