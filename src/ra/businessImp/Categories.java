package ra.businessImp;

import java.util.List;
import java.util.Scanner;

public class Categories implements Ishop {
    /*
    2. Xây dựng lớp Categories kế thừa IShop trong package ra.businessImp có các thông tin:
a. Các thuộc tính
• Id – int: Mã danh mục sản phẩm, tự tăng
• Name – String: Tên danh mục sản phẩm, không trùng lặp
• Descriptions – String: Mô tả danh mục sản phẩm
• Status – bit: Trạng thái danh mục (true – Hoạt động, false –không hoạt động)
b. Các constructors và phương thức getter/setter
c. Triển khai các phương thức:
• inputData(Scanner scanner): nhập thông tin cho danh mục
• displayData(): hiển thị thông tin danh mục
     */

//a. Các thuộc tính
    private  int id;
    private String name;
    private String descriptions;
    private boolean status;
    private static int  newCategoryId=4;

//b. Các constructors và phương thức getter/setter
    //constructors


    public Categories() {
        this.id = newCategoryId++;
    }

    public Categories(int id, String name, String descriptions, boolean status) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.status = status;
    }

    //phương thức getter


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    //phương thức setter


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //c. Triển khai các phương thức:
    //• inputData(Scanner scanner): nhập thông tin cho danh mục
    @Override
    public void inputData(Scanner scanner, List<Categories> categoriesList, List<Product> productList) {
        System.out.println("Nhập thông tin cho danh mục: ");
  //1. Id – int: Mã danh mục sản phẩm, tự tăng
        //a. Sinh ra mã danh mục
        //-Tìm mã danh mục lớn nhất trong mảng arrCategories
        int max=0;
        for (int i=0;i<categoriesList.size();i++){
            if(categoriesList.get(i).getId()>max){
                max=categoriesList.get(i).getId();
            }
        }
        this.id=max+1;
   //2. Name – String: Tên danh mục sản phẩm, không trùng lặp
        boolean check=true;
        System.out.println("Tên danh mục: ");
        do {
            this.name=scanner.nextLine();
            boolean isExit=false;
            for (int i=0;i<categoriesList.size();i++){
                if (categoriesList.get(i).getName().toLowerCase().equals(this.getName().toLowerCase())){
                    isExit=true;// bị trùng lặp
                    break;
                }
            }
            if (isExit){
                System.out.println("Tên danh mục đã tồn tại, vui long nhập lại.");
            }else {
                break;
            }

        }while (check);
 //3. Descriptions – String: Mô tả danh mục sản phẩm
        System.out.println("Mô tả danh mục:");
        this.descriptions=scanner.nextLine();
//4. Status – bit: Trạng thái danh mục (true – Hoạt động, false –không hoạt động)
        System.out.println("Trạng thái danh mục");
        do {
            String status=scanner.nextLine();
            if (status.equals("true")||status.equals("fasle")){
                this.status= Boolean.parseBoolean(status);
                break;
            }else {
                System.out.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
            }
        }while (check);
    }
    //• displayData(): hiển thị thông tin danh mục
    @Override
    public void displayData() {
        System.out.println("Mã danh mục:" +id);
        System.out.println("Tên danh mục: "+name);
        System.out.println("Mô tả danh mục :"+descriptions);
        System.out.println("Trạng thái danh mục: "+(status?"Hoạt động":"Không hoạt động"));
    }
    public void updateData(Scanner scanner, List<Categories> categoriesList){
        boolean isExits = true;
        System.out.println("Nhập tên");
        this.name = scanner.nextLine();
        System.out.println("Nhập mô tả");
        this.descriptions = scanner.nextLine();
        System.out.println("Nhập Trạng thái ( true or false )");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                this.status = Boolean.parseBoolean(status);
                break;
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
            }
        }while (isExits);
    }
}
