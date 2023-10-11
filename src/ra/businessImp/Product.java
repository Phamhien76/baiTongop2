package ra.businessImp;

import java.util.List;
import java.util.Scanner;

public class Product implements Ishop, Comparable<Product> {
    /*
    3. Xây dựng lớp Product kế thừa IShop trong package ra.businessImp có các thông tin:
a. Các thuộc tính
• Id – String: Mã sản phẩm, 4 ký tự, bắt đầu là P, không trùng lặp
• Name – String: Tên sản phẩm, không trùng lặp
• CatalogId – int – Mã danh mục của sản phẩm
• ImportPrice – float: Giá nhập sản phẩm, có giá trị lớn hơn 0
• ExportPrice – float: Giá bán sản phẩm tính theo công thức
ExprtPrice = ImportPrice * RATE
• Status: Trạng thái sản phẩm (True – Hoạt động, false – Không hoạt động)
b. Các constructors và phương thức getter/setter
c. Triển khai các phương thức:
• inputData(Scanner scanner): nhập thông tin cho sản phẩm
• displayData(): hiển thị thông tin sản phẩm
d. Xây dựng phương thức calExportPrice tính giá bán của sản phẩm
     */

    //a. Các thuộc tính
    private String id;
    private String name;
    private int catalogId;
    private float importPrice;
    private float exportPrice;
    private boolean status;


//b. Các constructors và phương thức getter/setter
    //Các constructors

    public Product() {
    }

    public Product(String id, String name, int catalogId, float importPrice, float exportPrice, boolean status) {
        this.id = id;
        this.name = name;
        this.catalogId = catalogId;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.status = status;
    }
    //phương thức getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public boolean isStatus() {
        return status;
    }

    //phương thức setter

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //c. Triển khai các phương thức:
    //• inputData(Scanner scanner): nhập thông tin cho sản phẩm
    //• Id – String: Mã sản phẩm, 4 ký tự, bắt đầu là P, không trùng lặp
    //• Name – String: Tên sản phẩm, không trùng lặp
    //• CatalogId – int – Mã danh mục của sản phẩm
    //• ImportPrice – float: Giá nhập sản phẩm, có giá trị lớn hơn 0
    //• ExportPrice – float: Giá bán sản phẩm tính theo công thức
    //ExprtPrice = ImportPrice * RATE
    //• Status: Trạng thái sản phẩm (True – Hoạt động, false – Không hoạt động)
    @Override
    public void inputData(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("nhập thông tin cho sản phẩm");
        boolean check = true;
        //1• Id – String: Mã sản phẩm, 4 ký tự, bắt đầu là P, không trùng lặp
        System.out.println("Mã sản phẩm:");
        do {
            this.id = scanner.nextLine();
            if (this.id.length() == 4) {
                //-Không trùng lặp
                boolean isExit = false;
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getId().toLowerCase().equals(this.getName().toLowerCase())) {
                        isExit = true;//bị trùng lặp
                        break;
                    }
                }
                if (isExit) {
                    System.out.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại!");
                } else {
                    break;
                }
            } else {
                System.out.println("Mã sản phẩm phải là 4 ký tự, xin vui lòng nhập lại");
            }
        } while (check);
        //2• Name – String: Tên sản phẩm, không trùng lặp
        System.out.println("Tên sản phẩm: ");
        do {
            this.name = scanner.nextLine();
            boolean isExit = false;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getName().toLowerCase().equals(this.name.toLowerCase())) {
                    isExit = true;//bị trung lặp
                    break;
                }
            }
            if (isExit) {
                System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại!");
            } else {
                break;
            }
        } while (check);

        //3• CatalogId – int – Mã danh mục của sản phẩm
        System.out.println("CatalogId: ");
        System.out.println("Chọn danh mục của sản phẩm:");

        do {
            for (int i = 0; i < categoriesList.size(); i++) {
                //hiển thị danh mục sản phẩm
                System.out.println(i + 1 + "." + categoriesList.get(i).getName());
            }
            System.out.println("Lựa chon của bạn");
            int choise = Integer.parseInt(scanner.nextLine());
            if (choise < 1 && choise > categoriesList.size()) {
                System.out.println("Không tồn tại mã danh mục, vui long chọn lại");
            } else {
                this.catalogId = categoriesList.get(choise - 1).getId();
                break;
            }

        } while (true);


        //4• ImportPrice – float: Giá nhập sản phẩm, có giá trị lớn hơn 0
        System.out.println("Giá nhập:");
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.out.println("Giá sản phẩm nhập vào phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (check);

        //5• ExportPrice – float: Giá bán sản phẩm tính theo công thức
        System.out.println("Giá bán:");
        this.exportPrice = Float.parseFloat(scanner.nextLine());

        //6• Status: Trạng thái sản phẩm (True – Hoạt động, false – Không hoạt động)
        System.out.println("Trạng thái của sản phẩm: ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    ;

    //• displayData(): hiển thị thông tin sản phẩm
    @Override
    public void displayData() {
        System.out.printf("Mã sản phẩm: %s-Tên sản phẩm:%s-CatalogId:%d\n", this.id, this.name, this.catalogId);
        System.out.printf("Giá sản phẩm nhập:%f-Giá sản phẩm:%f-Trạng thái:%s", this.importPrice, this.exportPrice, (this.status ? "Hoạt động" : "Không hoạt động"));
    }
    //d. Xây dựng phương thức calExportPrice tính giá bán của sản phẩm

    public void calExprtPrice() {
        exportPrice = importPrice * RATE;
    }


    @Override
    public int compareTo(Product o) {
        return (int) (this.importPrice - o.importPrice);
    }

    public void updateData(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        boolean isExit = true;
        System.out.println("Nhập tên sản phẩm");
        this.name = scanner.nextLine();
        System.out.println("Nhập Mã danh mục sản phẩm");
        do {
            this.catalogId = Integer.parseInt(scanner.nextLine());
            boolean isCatalogId = false;
            for (Categories categories : categoriesList) {
                if (categories.getId() == this.catalogId) {
                    isCatalogId = true;
                    isExit = false;
                    break;
                }
            }
            if (!isCatalogId) {
                System.out.println("Mã danh mục không hợp lệ , vui lòng nhập lại");
            } else {
                isExit = false;
            }
        } while (isExit);

        System.out.println("Nhập giá nhập sản phẩm có giá trị lớn hơn 0");
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice <= 0) {
                System.out.println("Giá nhập phải lớn hơn 0, vui lòngg nhập lại");
            } else {
                isExit = false;
            }
        } while (isExit);
        System.out.println("Nhập trạng thái sản phẩm");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                this.status = Boolean.parseBoolean(status);
                break;
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
            }
        } while (isExit);
    }
    public void getCatalogId(Scanner scanner, List<Categories> categoriesList, List<Product> productList){
        //3• CatalogId – int – Mã danh mục của sản phẩm
        System.out.println("CatalogId: ");
        System.out.println("Chọn danh mục của sản phẩm:");

        do {
            for (int i = 0; i < categoriesList.size(); i++) {
                //hiển thị danh mục sản phẩm
                System.out.println(i + 1 + "." + categoriesList.get(i).getName());
            }
            System.out.println("Lựa chon của bạn");
            int choise = Integer.parseInt(scanner.nextLine());
            if (choise < 1 && choise > categoriesList.size()) {
                System.out.println("Không tồn tại mã danh mục, vui long chọn lại");
            } else {
                this.catalogId = categoriesList.get(choise - 1).getId();
                break;
            }

        } while (true);
    }
}

