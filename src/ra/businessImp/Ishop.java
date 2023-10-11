package ra.businessImp;

import java.util.List;
import java.util.Scanner;

public interface Ishop {
    /*
1. Xây dựng interface IShop:
a. Hằng số float RATE = 1.3F
b. Các phương thức:
• inputData(Scanner scanner): Nhập thông tin cho đối tượng
• displayData(): Hiển thị thông tin đối tượng
 */
    public float RATE = 1.3F;
    void inputData(Scanner scanner, List<Categories> categoriesList, List<Product>productList);
    void displayData();
}
