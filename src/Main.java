import ra.businessImp.Categories;
import ra.businessImp.Product;
import ra.presentation.CategoriesManagement;
import ra.presentation.ProductManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
4. Xây dựng lớp Main thực hiện:
a. Khởi tạo danh sách danh mục sản phẩm và danh mục sản phẩm với List Interface và lớp ArrayList
b. Hiển thị menu và thực hiện các chức năng theo menu sau:
********************SHOP MENU*********************
1. Quản lý danh mục sản phẩm
2. Quản lý sản phẩm
3. Thoát
     */
    static List<Categories> categoriesList = new ArrayList<>();
    static List<Product> productList = new ArrayList<>();
    static {
       categoriesList.add(new Categories(1,"Danh muc 1","No",true));
        categoriesList.add(new Categories(2,"Danh muc 2","yes",false));
        categoriesList.add(new Categories(3,"Danh muc 3","No",true));

    }
    static {
        productList.add(new Product("Sp01","San pham 1",1, 2000,3000, true ));
        productList.add(new Product("Sp02","San pham 2",2, 2500,2400, false ));
        productList.add(new Product("Sp03","San pham 3",3, 1000,1400, true ));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choise;
        do {
            System.out.println("********************MENU*********************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println(" Hãy chọn chức năng:");

            choise = Integer.parseInt(scanner.nextLine());


            switch (choise) {
                case 1:
                    CategoriesManagement.showCategoriesMenu(scanner,categoriesList,productList);
                    break;
                case 2:
                    ProductManagement.showProductesMenu(scanner,categoriesList,productList);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Hãy chon các chức năng từ 1 đến 3!");
            }
        } while (true);
    }
}