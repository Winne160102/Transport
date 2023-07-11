package APIBaitap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Transport {
	//attributes
	private String id;
	private String name;
	private String cubicMeter;
	private float price;
	
	//constructor
	public Transport() {
	}
	//constructor all attributes
	public Transport(String id, String name, String cubicMeter, float price) {
		this.id = id;
		this.name = name;
		this.cubicMeter = cubicMeter;
		this.price = price;
	}
	
	//get & set
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCubicMeter() {
		return cubicMeter;
	}
	public void setCubicMeter(String cubicMeter) {
		this.cubicMeter = cubicMeter;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	//set all attributes
	public void setall (String id, String name, String cubicMeter, float price) {
		this.id = id;
		this.name = name;
		this.cubicMeter = cubicMeter;
		this.price = price;
	}
	// input
	public void inputTransport() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter ID: ");
		id = sc.nextLine();
		
		System.out.println("Enter Name: ");
		name = sc.nextLine();
		
		System.out.println("Enter CubicMeter: ");
		cubicMeter = sc.nextLine();
		
		System.out.println("Enter Price: ");
		price = new Scanner(System.in).nextFloat();
		while(price <= 0) {
			System.out.println("Enter Price Again: ");
			price = new Scanner(System.in).nextFloat();
		}
	}
	// output
	public void outputTransport() {
		System.out.println("ID: " + getId());
		
		System.out.println("Name: " + getName());
		
		System.out.println("CubicMeter: " + getCubicMeter());
		
		System.out.println("Price: " + getPrice());
	}
	
	//inputFile Trả về 1 list
	public static ArrayList<Transport> inputFile() {
		//khởi tạo 1 arraylist
		ArrayList<Transport> list = new ArrayList<Transport>();
		try {
			//khởi tạo fReader để đọc file và đưa đường link vào
			FileReader fReader = new FileReader("C:\\Users\\Admin\\Desktop\\lib java\\Java\\Trans.txt");
			//khởi tạo bReader, tạo bộ nhớ đệm để lưu 
			BufferedReader bReader = new BufferedReader(fReader);
			while(true) {
				//tạo biến line với kiểu dữ liệu là string đọc từng dòng và lưu vào biến bReader
				String line = bReader.readLine();
				//Nếu line == rỗng hoặc không có giá trị thì thoát khỏi vòng while
				if(line == "" || line == null) break;
				//Tạo một biến temp kiểu dữ liệu Mảng để sau khi cắt chuỗi bằng split thì lưu vào temp
				String[] temp = line.split("[;]");
				//khởi tạo data và và đưa vào đó các temp sau khi đã cắt vào constructor 
				Transport data = new Transport(temp[0], temp[1], temp[2], Float.parseFloat(temp[3]));
				//data sẽ được thêm vào liss bằng add
				list.add(data);
			}
			//đóng bộ nhớ đệm
			bReader.close();
			//đóng đọc file
			fReader.close();
			//in lỗi
		} catch (Exception e) {
			e.printStackTrace();
		}
		//trả về list
		return list;
	}
	
	//output File
	//Không cần trả về vì dùng void và đưa thuộc tính vào outputFile là list
	public static void outputFile(ArrayList<Transport> list) {
		try {
			//khởi tạo fWriter để ghi file và đưa đường dẫn vào 
			FileWriter fWriter = new FileWriter("C:\\Users\\Admin\\Desktop\\lib java\\Java\\Trans1.txt");
			//tạo bộ nhớ đệp để khi ghi xong sẽ được đưa vào bộ nhớ đệm fWriter
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			//tạo một vòng lặp để duyệt các đối tượng có trong list 
			for(Transport data : list) {
				//write để ghi còn bWriter là bộ nhớ đệm => câu lệnh có nghĩa là ghi vào bộ nhớ đệm
				bWriter.write(data.getId() + ";" + data.getName() + ";" + data.getCubicMeter()+ ";" + data.getPrice());
				//khi đã ghi hết thì nó xuống dòng
				bWriter.newLine();
			}
			//đóng bộ nhớ đệm
			bWriter.close();
			//đóng ghi file
			fWriter.close();
			//in lỗi
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//output Monitor
	public void outputMonitor() {
		Scanner sc = new Scanner(System.in);
		//tạo 1 biến exit với kiểu boolean và cho nó false
		boolean exit = false;
		//khởi tạo 1 arraylist 
		ArrayList<Transport> list = new ArrayList<Transport>();
		//vòng while !exit có nghĩa là exit = true để tiếp tục vòng lặp
		while(!exit) {
			System.out.println("Enter your Choice: 1 - Input File , 2 - Output File, 3 - Input and Output from keyboard, 4 - exit");
			//điền choice
			int choice = sc.nextInt();
			//lựa chọn
			switch (choice) {
			// nếu chọn 1 thì sẽ inputFile
			case 1:
				//vì inputFile đang là hàm trả về khi đó mình phải cho list = inputFile
				list = inputFile();
				System.out.println("Input file created. ");
				break;
			//nếu chọn 2 thì sẽ outputFile
			case 2:
				//vì outputFile là hàm void nên phải đưa thuộc tính vào 
				outputFile(list);
				System.out.println("Output file created. ");
				break;
			//nếu chọn 3 phải nhập để hiện thị			
			case 3:
				System.out.println("Enter the information for Transport: ");
				inputTransport();
				outputTransport();
				break;
			//chọn 4 để thoát
			case 4:
				// sau đó cho exit = true thì !exit = false => thoát vòng lặp
				exit = true;
				System.out.println("The Program has been closed. ");
				break;
			// nếu chọn khác 1 2 3 4 phải chọn lại
			default:
				System.out.println("Invalid choice: ");
				break;
			}
		}
	}
	public static void main(String[] args) {
		Transport transport = new Transport();
		transport.outputMonitor();
	}
}
