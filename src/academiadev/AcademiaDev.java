/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academiadev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author tiago
 */
public class AcademiaDev {

    public static void main(String[] args) {

        List<Product> products = new ArrayList();
        List<Purchase> purchases = new ArrayList();
        List<Sale> sales = new ArrayList();
        List<Result> rs = new ArrayList<>();

        try {
            String ln;
            int c = 0;

            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Temp\\catalog.csv").getAbsoluteFile()));
            while ((ln = br.readLine()) != null) {
                if (c == 0) {
                    c++;
                    continue;
                }
                Product e = new Product();
                String colunas[] = ln.split(",");
                e.setProductId(colunas[0].replace("\"", ""));
                e.setPrice(Double.parseDouble(colunas[1].replaceAll("[\"R$]", "")));

                products.add(e);
                c++;
            }
            br.close();

            br = new BufferedReader(new FileReader(new File("C:\\Temp\\purchases.jsonl").getAbsoluteFile()));
            while ((ln = br.readLine()) != null) {
                JSONObject obj = (JSONObject) new JSONParser().parse(ln);

                Purchase p = new Purchase();
                p.setnPayment(Integer.parseInt(obj.get("n_payments").toString()));
                p.setDate(LocalDate.parse(obj.get("timestamp").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                p.setPrice(Double.parseDouble(obj.get("price").toString().replaceAll("[R$]", "")));
                p.setPurchaseId(obj.get("purchase_id").toString());
                p.setPaymentMethod(obj.get("payment_method").toString());

                purchases.add(p);
            }
            br.close();

            br = new BufferedReader(new FileReader(new File("C:\\Temp\\sales.jsonl").getAbsoluteFile()));
            while ((ln = br.readLine()) != null) {
                JSONObject obj = (JSONObject) new JSONParser().parse(ln);

                Sale s = new Sale();
                s.setnPayment(Integer.parseInt(obj.get("n_payments").toString()));
                s.setSaleId(obj.get("sale_id").toString());
                s.setDate(LocalDate.parse(obj.get("timestamp").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                s.setPaymentMethod(obj.get("payment_method").toString());
                s.setProductId(obj.get("product_id").toString());

                sales.add(s);
            }
            br.close();

            purchases.forEach((Purchase a) -> {
                if (a.getPaymentMethod().equalsIgnoreCase("debit")) {
                    Result r = new Result();
                    r.setData(a.getDate());
                    r.setTotal(-a.getPrice());
                    rs.add(r);
                } else {
                    int i = 0;
                    while (i < a.getnPayment()) {
                        Result r = new Result();
                        LocalDate ld;
                        if (a.getDate().getDayOfMonth() >= 5) {
                            ld = LocalDate.of(a.getDate().plusMonths(1).getYear(),
                                    a.getDate().plusMonths(1).getMonthValue(), 10);
                        } else {
                            ld = LocalDate.of(a.getDate().getYear(), a.getDate().getMonthValue(), 10);
                        }

                        r.setData(ld.plusMonths(i));
                        r.setTotal(-(a.getPrice() / a.getnPayment()));
                        i++;

                        rs.add(r);
                    }
                }
            });

            sales.forEach((a) -> {
                if (a.getPaymentMethod().equalsIgnoreCase("debit")) {
                    Result r = new Result();
                    r.setData(a.getDate());
                    r.setTotal((products.stream()
                            .filter(b -> Objects.equals(b.getProductId(), a.getProductId()))
                            .collect(Collectors.toList()).get(0).getPrice()));
                    rs.add(r);
                } else {
                    int i = 0;
                    while (i < a.getnPayment()) {
                        Result r = new Result();
                        LocalDate ld;
                        if (a.getDate().getDayOfMonth() >= 5) {
                            ld = LocalDate.of(a.getDate().plusMonths(1).getYear(),
                                    a.getDate().plusMonths(1).getMonthValue(), 10);
                        } else {
                            ld = LocalDate.of(a.getDate().getYear(), a.getDate().getMonthValue(), 10);
                        }

                        r.setData(ld.plusMonths(i));
                        r.setTotal((products.stream()
                                .filter(b -> Objects.equals(b.getProductId(), a.getProductId()))
                                .collect(Collectors.toList()).get(0).getPrice()) / a.getnPayment());
                        i++;

                        rs.add(r);
                    }
                }
            });

            Map<LocalDate, List<Result>> m = new TreeMap<>(rs.stream().collect(Collectors.groupingBy(a -> a.getData())));
            Double sm = 0.0;

            System.out.println("{\n"
                    + "\"token\": \"557fbd248fed216a33fed923fac5d77bf0da504c\",\n"
                    + "\"email\": \"tiagofer_alves@yahoo.com.br\",\n"
                    + "\"answer\": [");
            for (Map.Entry<LocalDate, List<Result>> entry : m.entrySet()) {
                sm += entry.getValue().stream().mapToDouble(Result::getTotal).sum();
                System.out.println("{\"date\": \"" + entry.getKey() + "\", \"value\": " + Math.round(sm) + "},");
            }
            System.out.println("]}");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
