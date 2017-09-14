package com.iba.edu;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Cloudant_test {

    public static  void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "cloudant.properties";
            input = Cloudant_test.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        CloudantClient client = ClientBuilder.account(prop.getProperty("account"))
                .username(prop.getProperty("user"))
                .password(prop.getProperty("password"))
                .build();

        System.out.println("-----------------------------------------");
        System.out.println("Connected to Cloudant");
        System.out.println("Server Version: " + client.serverVersion());
        System.out.println("-----------------------------------------");

        List<String> databases = client.getAllDbs();
        System.out.println("-----------------------------------------");
        System.out.println("All my databases : ");
        for ( String db : databases ) {
            System.out.println(db);
        }

        

    }
}
