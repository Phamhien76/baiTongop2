package ra.presentation;

import ra.businessImp.Categories;
import ra.businessImp.Product;

import java.util.List;
import java.util.Scanner;
/*
5. Xây dựng lớp CategoriesManagement trong package ra.presentation hiển thị menu và thực hiện các menu
********************CATEGORIES MENU********************
1. Danh sách danh mục
2. Thêm mới danh mục
3. Cập nhật thông tin danh mục (Cập nhật theo mã)
4. Xóa danh mục (Chỉ cho phép xóa danh mục chưa có sản phẩm)
5. Thoát

 */

public class CategoriesManagement {
    public static void showCategoriesMenu(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        boolean isExit = true;
        do {
            System.out.println("********************MENU*********************");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Cập nhật thông tin danh mục (Cập nhật theo mã)");
            System.out.println("4. Xóa danh mục (Chỉ cho phép xóa danh mục chưa có sản phẩm)");
            System.out.println("5. Thoát");
            System.out.println(" Hãy chọn chức năng:");

            int choise = Integer.parseInt(scanner.nextLine());


            switch (choise) {
                case 1:
                    diplayCategories(categoriesList);
                    break;
                case 2:
                    addnewCatalog(scanner, categoriesList, productList);
                    break;
                case 3:
                    editCatalog(scanner,categoriesList, productList);
                    break;
                case 4:
                    deledeCategories(scanner,categoriesList, productList);
                    break;
                case 5:
                    isExit = false;
                    break;
            }
        } while (isExit);
    }

    //"1. Danh sách danh mục");
    public static void diplayCategories(List<Categories> categoriesList) {
        for (Categories categories : categoriesList) {
            categories.displayData();
        }

    }

    //2. Thêm mới danh mục
    public static void addnewCatalog(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("nhập số danh mục cần thêm mới:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Categories newcategories = new Categories();
            newcategories.inputData(scanner, categoriesList, productList);
            categoriesList.add(newcategories);
        }
    }

    //3. Cập nhật thông tin danh mục (Cập nhật theo mã)");
    public static boolean editCatalog(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập mã danh mục cần edit:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        //Lấy chỉ số phần tử cần cập nhật
        int indexEdit = getIndexCatalogOfList(catalogId, categoriesList);
        if (indexEdit >= 0) {
            //Tìm được phần tử cần cập nhật
            System.out.println("Nhập tên danh mục cần cập nhật");
            String catalogName = scanner.nextLine();
            if (catalogName != "" && catalogName.trim().length() > 0) {
                boolean isExist = false;
                for (int i = 0; i < categoriesList.size(); i++) {
                    if (i != indexEdit && categoriesList.get(i).getName().equals(catalogName)) {
                        isExist=true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Tên danh mục đã tồn tại!");
                    return false;
                }
                categoriesList.get(indexEdit).setName(catalogName);
            }
            System.out.println("Nhập vào mô tả dan mục cần cập nhật:");
            String description = scanner.nextLine();
            if (description !=""&&description.trim().length()>0){
                categoriesList.get(indexEdit).setDescriptions(description);
            }
            System.out.println("Nhâp vào trạng thái cần cập nhật:");
            String status = scanner.nextLine();
            if (status !=""&&status.trim().length()>0){
                categoriesList.get(indexEdit).setStatus(Boolean.parseBoolean(status));
            }
        }else {
            System.err.println("Không tồn tại mã danh mục");
            return false;
        }
        return true;

    }

    //4. Xóa danh mục (Chỉ cho phép xóa danh mục chưa có sản phẩm)");
    public static void deledeCategories(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexCatalogOfList(catalogId, categoriesList);
        if (indexDelete>=0){
            //Kiểm tra danh mục chứa sản phẩm chưa, chưa --> xóa
            boolean check=false;
            for (Product product:productList){
                if (product.getCatalogId()==catalogId){
                    check=true;
                }
            }
            if (check){
                System.err.println("Danh mục đã chứa sản phẩm, không thể xóa");
            }else {
                //Thực hiện xóa
                categoriesList.remove(indexDelete);
            }
        }else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static int getIndexCatalogOfList(int catalogId, List<Categories> categoriesList) {
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getId() == catalogId) {
                return i;//Có tồn tại
            }
        }
        return -1;//Không tồn tại
    }
}
