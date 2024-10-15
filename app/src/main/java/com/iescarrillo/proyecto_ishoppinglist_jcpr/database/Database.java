package com.iescarrillo.proyecto_ishoppinglist_jcpr.database;

import com.iescarrillo.proyecto_ishoppinglist_jcpr.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Product> productList;

    public static void initializeList(){
        if(productList == null){
            productList = new ArrayList<>();

            Product product1 = new Product();

            product1.setId(1);
            product1.setProductName("Yogur Griego");
            product1.setInfoNote("Yogur cremoso, sin azúcares añadidos y con alto contenido en proteínas.");
            product1.setPendStatus(true);

            Product product2 = new Product();

            product2.setId(2);
            product2.setProductName("Aceite de Oliva Virgen Extra");
            product2.setInfoNote("Aceite prensado en frío, rico en antioxidantes y grasas saludables.");
            product2.setPendStatus(true);

            Product product3 = new Product();

            product3.setId(3);
            product3.setProductName("Chocolate Negro");
            product3.setInfoNote("Chocolate intenso, bajo en azúcar y con alto contenido de antioxidantes.");
            product3.setPendStatus(true);

            Product product4 = new Product();

            product4.setId(4);
            product4.setProductName("Barrita Energética");
            product4.setInfoNote("Snack natural rico en fibra y energía, hecho con frutos secos y miel.");
            product4.setPendStatus(true);

            Product product5 = new Product();

            product5.setId(5);
            product5.setProductName("Mango");
            product5.setInfoNote("Fruta tropical dulce y jugosa, rica en vitaminas A y C.");
            product5.setPendStatus(true);

            productList.add(product1);
            productList.add(product2);
            productList.add(product3);
            productList.add(product4);
            productList.add(product5);

        }
    }

}
