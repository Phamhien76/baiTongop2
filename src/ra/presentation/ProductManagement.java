package ra.presentation;

import ra.businessImp.Categories;
import ra.businessImp.Product;

import java.util.*;

public class ProductManagement {
    /*
    6. Xây dựng lớp ProductManagement trong package ra.presentation hiển
    thị menu và thực hiện các menu
    ********************CATEGORIES MENU********************
    1. Danh sách sản phẩm
    2. Thêm mới sản phẩm (Khi thêm mới cho phép chọn danh mục)
    3. Cập nhật thông tin sản phẩm (Cập nhật theo mã)
    4. Xóa sản phẩm
    5. Sắp xếp sản phẩm theo giá bán tăng dần
    6. Sắp xếp sản phẩm theo giá nhập giảm dần
    7. Tìm kiếm sản phẩm theo tên sản phẩm
    8. Thoát
     */
    public static void showProductesMenu(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        boolean isExit = true;
        do {
            System.out.println("********************MENU*********************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm (Khi thêm mới cho phép chọn danh mục)");
            System.out.println("3. Cập nhật thông tin sản phẩm (Cập nhật theo mã)");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá bán tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo giá nhập giảm dần");
            System.out.println("7. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("8. Thoát");
            System.out.println(" Hãy chọn chức năng:");

            int choise = Integer.parseInt(scanner.nextLine());


            switch (choise) {
                case 1:
                    diplayProduct(scanner, categoriesList,  productList);
                    break;
                case 2:
                    addnewProduct(scanner,categoriesList,productList);
                    break;
                case 3:
                    updateProduct(scanner,categoriesList,productList);
                    break;
                case 4:
                    deledeProduct(scanner,categoriesList,productList);
                    break;
                case 5:
                    sortImportPrice(scanner,categoriesList,productList);
                    break;
                case 6:
                    sortExportPrice(productList);
                    break;
                case 7:
                    searchProduct(scanner,categoriesList,productList);
                    break;
                case 8:
                    isExit = false;
                    break;
            }
        } while (isExit);
    }

    //1."1. Danh sách sản phẩm");
    public static void diplayProduct(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).displayData();
        }
    }

    //2. Thêm mới danh mục
    public static void addnewProduct(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("nhập vào số lượng sản phẩm cần nhập dữ liệu:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product newProduct = new Product();
            newProduct.inputData(scanner, categoriesList, productList);
            productList.add(newProduct);
            System.out.println("Đã thêm mới danh mục sản phẩm");
        }
    }

    //3. Cập nhật thông tin danh mục (Cập nhật theo mã)");
    public static void updateProduct(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        Product newProduct=new Product();
        newProduct.getCatalogId(scanner, categoriesList, productList);
        System.out.println("Nhập mã sản phẩm cần edix:");
        String productIdUpdate = scanner.nextLine();
        // Tìm sản phẩm trong danh sách sản phẩm dựa trên mã sản phẩm
        Product productToUpdate = null;
        for (Product product : productList) {
            if (product.getId().equals(productIdUpdate)) {
                productToUpdate = product;
                break;
            }
        }if (productToUpdate!=null){
            // Nếu sản phẩm được tìm thấy (không null), tiến hành cập nhật thông tin sản phẩm:
            System.out.println("Nhập tên sản phẩm: ");
            String  productName=scanner.nextLine();
            if (!productName.trim().isEmpty()){
                productToUpdate.setName(productName);
            }
            // Hiển thị danh sách mã danh mục và yêu cầu người dùng nhập mã danh mục mới
            System.out.println("Danh sách mã danh mục: ");
            for (Categories categories:categoriesList){
                System.out.println("Mã: "+ categories.getId()+"Tên: "+categories.getName());
            }
            System.out.println("Nhập mã danh mục mới: ");
            int productCatalogId = Integer.parseInt(scanner.nextLine());
            // Kiểm tra tính hợp lệ của mã danh mục
            boolean check = false;
            for (Categories categories:categoriesList){
                if (categories.getId()==productCatalogId){
                    check=true;
                    break;
              }
            }
            if (check){
                productToUpdate.setCatalogId(productCatalogId);
            }else {
                System.err.println("Mã danh mục không hợp lệ");
            }

            System.out.println("Nhập giá nhập: ");
            float newimportPrice = Float.parseFloat(scanner.nextLine());
            if (newimportPrice>0){
               productToUpdate.setImportPrice(newimportPrice);
            }else {
                System.err.println("Giá nhập không hợp lệ, vui lòng nhập lại");
            }

            System.out.println("Nhập trạng thái sản phẩm (true - Hoạt động, false - Không hoạt động):");
            boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
            productToUpdate.setStatus(newStatus);

            System.out.println("Thông tin sản phẩm đã được cập nhật.");
        } else {
            // Nếu sản phẩm không được tìm thấy (null)
            System.err.println("Mã sản phẩm không tồn tại.");
        }
    };

    //4. Xóa danh mục (Chỉ cho phép xóa danh mục chưa có sản phẩm)");
    public static void deledeProduct(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        Product newProduct=new Product();
        newProduct.getCatalogId(scanner, categoriesList, productList);
        boolean isExit = true;
        System.out.println("Nhập mã sản phẩm cần delete:");
        int deleteId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < productList.size(); i++) {
            isExit = true;
            if (productList.get(i).getId().equals(deleteId)) {
                productList.remove(i);
                System.out.println("Xóa sản phẩm thành công");
            } else {
                isExit = false;
            }
        }
        if (isExit = false) {
            System.out.println("Không tìm thấy mã sản phẩm cần xóa, vui lòng nhập lại");
        }
    }

    //5. Sắp xếp sản phẩm theo giá bán tăng dần
    public static void sortImportPrice(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        Collections.sort(productList);

    }

    //6. Sắp xếp sản phẩm theo giá nhập giảm dần
    public static void sortExportPrice(List<Product> productList) {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (o1.getImportPrice()-o2.getImportPrice()>0)?1:(o1.getImportPrice()<o2.getImportPrice())?-1:0;
            }
        });

    }

    //7. Tìm kiếm sản phẩm theo tên sản phẩm
    public static void searchProduct(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập mã sản phẩm cần tìm kiếm:");
        String seacherId=scanner.nextLine();
        for (int i=0;i<productList.size();i++){
            if (productList.get(i).getId().equals(seacherId)){
                //Product newProduct=new Product();
                productList.get(i).displayData();
            }
        }


    }

    public static int getIndexOfArrCategories(int catalogId, List<Categories> categoriesList, List<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getCatalogId() == catalogId) {
                return i;//Có tồn tại
            }
        }
        return -1;//Không tồn tại
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
