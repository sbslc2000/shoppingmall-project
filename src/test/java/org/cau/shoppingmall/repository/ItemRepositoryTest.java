package org.cau.shoppingmall.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cau.shoppingmall.entity.item.*;
import org.cau.shoppingmall.repository.item.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

//@SpringBootTest
//@Transactional
class ItemRepositoryTest {


    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private StockDetailsRepository stockDetailsRepository;

    private Seller getSeller(Long id) {
        return null;
    }

    private List<List<String>> readCSVFile(String filePath) {

        List<List<String>> list = new ArrayList<List<String>>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = Files.newBufferedReader((Paths.get(filePath)));
            String line = "";
            while((line = bufferedReader.readLine()) != null) {

                List<String> stringList = new ArrayList<>();
                String stringArray[] = line.split(",");

                stringList = Arrays.asList(stringArray);
                list.add(stringList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private Item createItem(String name, Seller seller, int price, int quantity, Category category, String img) {
        Item buildedItem = new Item().builder()
                .name(name)
                .seller(seller)
                .price(price)
                .quantity(quantity)
                .category(category)
                .img(img)
                .build();

        return null;
    }

    private Color saveAndGetColor(String name) {
        Color buildColor = new Color().builder()
                .name(name)
                .build();

        Color savedColor = colorRepository.save(buildColor);
        return savedColor;
    }

    private Color getColor(String name) {
        Color color = colorRepository.findByName(name).get();
        return color;
    }

   //@Test
    void setItemsByCSVFile() {

        ObjectMapper mapper = new ObjectMapper();
        int imgid = 0;


        List<List<String>> lists = readCSVFile("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\데이터incsv.csv");

        for(List<String> line : lists) {
            Seller seller;
            Category category;
            String name = line.get(0);
            String sellerName = line.get(1);
            int price = Integer.parseInt(line.get(2));
            long categoryId = Long.parseLong(line.get(3));

            Optional<Seller> findSeller = sellerRepository.findByName(sellerName);

            if(findSeller.isPresent()) {
                seller = findSeller.get();
            } else {
                Seller buildedSeller = new Seller().builder()
                        .name(sellerName)
                        .build();

                Seller savedSeller = sellerRepository.save(buildedSeller);
                seller = savedSeller;
            }

            category = categoryRepository.findById(categoryId).get();



            int colorSelector;
            List<Color> colorList = new ArrayList<>();
            String colorName;

            List<String> imgList = new ArrayList<>();


            //colorList에 color 세팅
            for(colorSelector = 4 ; colorSelector < line.size() ; colorSelector++)
            {

                colorName = line.get(colorSelector);
                if(colorName == null || colorName.equals("")) {
                    continue;
                }

                File imgFile = new File("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\작업용\\"+imgid+".jpg");
                imgid++;
                String uuid = UUID.randomUUID().toString();
                File newFile = new File("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\작업용\\"+uuid+".jpg");
                imgFile.renameTo(newFile);
                imgList.add(uuid);

                Optional<Color> findColor = colorRepository.findByName(colorName);
                Color color;
                if(findColor.isEmpty()) {
                    color = saveAndGetColor(colorName);
                } else {
                    color = findColor.get();
                }
                colorList.add(color);
            }

            String img = imgList.toString();
            System.out.println("img = " + img);
            imgList.clear();

            Item buildedItem = new Item().builder()
                    .name(name)
                    .seller(seller)
                    .price(price)
                    .sales(0)
                    .category(category)
                    .img(img)
                    .reviews(0)
                    .averageStars(0)
                    .likes(0)
                    .baskets(0)
                    .quantity(0)
                    .build();

            Item savedItem = itemRepository.save(buildedItem);


        }


        //setStockDetails

        setStockDetails();
    }

    @Transactional
    public void setStockDetails() {
        List<List<String>> lists = readCSVFile("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\stockDetails_justCsv.csv");
        Long itemId = 0L;
        for (List<String> line : lists) {
            itemId++;
            Item item = itemRepository.findById(itemId).get();
            int iterator = 0;
            int sumOfQuantity = 0;
            while (true) {
                if (line.get(iterator).replace("\uFEFF","").equals("END")) {
                    item = itemRepository.findById(itemId).get();
                    System.out.println("sumOfQuantity = " + sumOfQuantity);
                    item.changeQuantity(sumOfQuantity);
                    itemRepository.save(item);
                    break;
                } else if (line.get(iterator).replace("\uFEFF","").equals("AND")) {
                    iterator++;
                } else {
                    String colorName = line.get(iterator++).replace("\uFEFF","");
                    System.out.println("colorName= "+colorName);
                    Color findColor = colorRepository.findByName(colorName).get();
                    while (true) {
                        if(line.get(iterator).replace("\uFEFF","").equals("AND") || line.get(iterator).replace("\uFEFF","").equals("END")) {
                            break;
                        }
                        String size = line.get(iterator).replace("\uFEFF","");
                        System.out.println("size = "+size);
                        Size findSize = sizeRepository.findByName(size).get();


                        int quantity = Integer.parseInt(line.get(iterator + 1).replace("\uFEFF",""));

                        sumOfQuantity += quantity;
                        StockDetails buildStockDetails = new StockDetails().builder()
                                .color(findColor)
                                .size(findSize)
                                .quantity(quantity)
                                .item(item)
                                .build();

                        item = itemRepository.findById(itemId).get();
                        StockDetails savedStockDetails = stockDetailsRepository.save(buildStockDetails);
                        savedStockDetails.setItem(item);
                        iterator += 2;
                    }

                }

            }

        }
    }


    @Test
    void colorRepositoryTest() {
        List<List<String>> lists = readCSVFile("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\stockDetails_justCsv.csv");
        //List<List<String>> lists = readCSVFile("C:\\Users\\SeoBeomseok\\Desktop\\쇼핑몰\\stockdetails_csv.csv");
        String str = lists.get(0).get(0);
        System.out.println(" lists.get(0).get(0); = " +  str);
        assertThat(str.replace("\uFEFF","")).isEqualTo("화이트");



    }

}