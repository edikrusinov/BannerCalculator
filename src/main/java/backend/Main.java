package main.java.backend;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        double weight;
        double height;
        double s;
        double p;
        int designPrice = 5000;
        boolean frame = false;
        boolean edgeSealing = false;
        boolean grommet = false;
        boolean installation = false;
        boolean design = false;
        int sum = 0;



        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextDouble()) {
                weight = scanner.nextDouble();
                break;
            } else {
                System.out.println("Данные введены не верно");
                scanner.next();
            }
        }

        while (true) {
            if (scanner.hasNextDouble()) {
                height = scanner.nextDouble();
                break;
            } else {
                System.out.println("Данные введены не верно");
                scanner.next();
            }
        }




        PrintPrice printPrice = new PrintPrice(weight, height);
        FramePrice framePrice = new FramePrice(weight, height);
        EdgeSealingPrice sealingPrice = new EdgeSealingPrice(weight, height);
        GrommetPrice grommetPrice = new GrommetPrice(sealingPrice.getPerimeter());
        InstallationPrice installationPrice = new InstallationPrice(framePrice.getNumberOfBeams(), printPrice.getS());

        s = printPrice.getS();
        p = sealingPrice.getPerimeter();

        System.out.println(s);
        sum += printPrice.priceCalculation();
        if (edgeSealing) {
            sum += sealingPrice.getEdgeSealingPrice();
            System.out.println("Сумма за проклейку: " + sealingPrice.getEdgeSealingPrice());
            System.out.println("Периметр: " + p);

            if (grommet) {
                sum += grommetPrice.getGrommetAmountSum();
                System.out.println("Количество люверсов: " + grommetPrice.getGrommetAmount());
                System.out.println("Сумма за люверсы: " + grommetPrice.getGrommetAmountSum());
            }

            if (installation) {
                sum += installationPrice.installationPriceSealing();
                System.out.println("Сумма за установку: " + installationPrice.installationPriceFrameAndSealing());
            }
        }
        if (!edgeSealing && frame) {
            sum += framePrice.getFramePrice();
            System.out.println("Количество бруса: " + framePrice.getNumberOfBeams());
            System.out.println("Сумма за каркас: " + framePrice.getFramePrice());
        }
        if (edgeSealing && frame && installation) {
            sum += installationPrice.installationPriceFrameAndSealing();
            System.out.println("Периметр: " + p);
            System.out.println("Количество брусков: " + framePrice.getNumberOfBeams());
            System.out.println("Сумма за каркас, проклейку и монтаж: " + installationPrice.installationPriceFrameAndSealing());
        } else if (edgeSealing && frame && !installation) {
            System.out.println("Каркас и проклейка одновременно без монтажа не делаются");
            return;
        }
        if (frame) {
            sum += framePrice.getFramePrice();
            System.out.println("Количество бруса: " + framePrice.getNumberOfBeams());
            System.out.println("Стоимость за каркас: " + framePrice.getFramePrice());
            if (installation) {
                sum += installationPrice.installationPriceFrame();
                System.out.println("Стоимость монтажа: " + installationPrice.installationPriceFrame());
            }
        }
        if (design) {
            sum += designPrice;
            System.out.println("Цена за дизайн: " + designPrice);
        }
        System.out.println("Сумма: " + sum);
        System.out.println(s);


            }
        }

