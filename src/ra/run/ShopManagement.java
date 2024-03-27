package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    Categories[] arrCategories = new Categories[100];
    Product[] arrProducts = new Product[100];
    int indexCategories = 0;
    int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();
        do {
            System.out.println("************SHOP MENU************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.displayCategoriesMenu(scanner, shopManagement);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public void displayCategoriesMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin các danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListCategories(scanner);
                    break;
                case 2:
                    shopManagement.displayListCategories();
                    break;
                case 3:
                    shopManagement.updateCategories(scanner);
                    break;
                case 4:
                    shopManagement.deleteCategories(scanner);
                    break;
                case 5:
                    shopManagement.updateCategorieStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public void inputListCategories(Scanner scanner) {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int numberOfCategories = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCategories; i++) {
            arrCategories[indexCategories] = new Categories();
            arrCategories[indexCategories].inputData(scanner, arrCategories, indexCategories);
            indexCategories++;
        }
    }

    public void displayListCategories() {
        for (int i = 0; i < indexCategories; i++) {
            arrCategories[i].displayData();
        }
    }

    public void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndexById(catalogId);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trạng thái");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        arrCategories[indexUpdate].setDescription(scanner.nextLine());
                        break;
                    case 3:
                        arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public int getIndexById(int catalogId) {
        for (int i = 0; i < indexCategories; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(catalogId);
        if (indexDelete >= 0) {
            //Kiểm tra danh mục có sản phẩm chưa
            boolean isExistProduct = false;
            for (int i = 0; i < indexProduct; i++) {
                if (arrProducts[i].getCatalogId() == catalogId) {
                    isExistProduct = true;
                    break;
                }
            }
            if (isExistProduct) {
                System.err.println("Danh mục đang chứa sản phẩm, không thể xóa");
            } else {
                //Thực hiện xóa
                for (int i = indexDelete + 1; i < indexCategories; i++) {
                    arrCategories[i - 1] = arrCategories[i];
                }
                //Gán phần tử cuối là null
                arrCategories[indexCategories - 1] = null;
                //giảm chỉ số đi 1
                indexCategories--;
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public void updateCategorieStatus(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = getIndexById(catalogId);
        if (indexUpdateStatus>=0){
            arrCategories[indexUpdateStatus].setCatalogStatus(!arrCategories[indexUpdateStatus].isCatalogStatus());
        }else{
            System.err.println("Mã danh mục không tồn tại");
        }
    }
}
