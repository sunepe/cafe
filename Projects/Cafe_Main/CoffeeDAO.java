package Cafe_Main;

import java.io.FileOutputStream;
import java.io.OutputStream;

// db 연동 class

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

// db연결
public class CoffeeDAO {

	private String driver = "oracle.jdbc.driver.OracleDriver" ;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
	private String username = "scott" ;
	private String password = "tiger" ;
	private Connection conn = null ;	

	public CoffeeDAO() {		
		try {
			Class.forName(driver) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 발견되지 않습니다(jar 파일 누락)"); 
			e.printStackTrace();		
		}
	}

	private Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password) ;
		} catch (SQLException e) {
			System.out.println("커넥션 생성 오류");
			e.printStackTrace();
		}
		return conn ;
	}

	private void closeConnection() {
		try {
			if(conn != null) {conn.close(); }
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}		
	}

	// 로그인
	public int login(String id, String passwd) throws Exception{

		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql="";
		String password="";
		int result = -1;

		try{
			conn =getConnection();
			sql ="select password from login where id = ?";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, id);

			rs=pstmt.executeQuery(); // select문은 executeQuery()

			if(rs.next()){
				password =rs.getString("password");
				if(password.equals(passwd)) {
					result=1; //인증성공
				} else {
					result=0; //비밀번호 틀림
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}try {
			if(rs != null) {rs.close(); }
			if(pstmt != null) {pstmt.close(); }
			closeConnection() ;
		} catch (Exception e2) {
			e2.printStackTrace(); 
		}
		return result;
	}

	// 메뉴 추가
	public int menuAdd(String menuCode, String menuName, int menuPrice){//콘솔창에서 데이터를 입력받아 객체 생성
		int result =-1;
		PreparedStatement pstmt =null;
		ResultSet rs = null;		

		try {
			conn = getConnection();
			String sql = "insert into coffeemenu (menucode, menu, price) values (?, ?, ?)";
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, menuCode);
			pstmt.setString(2, menuName);
			pstmt.setInt(3, menuPrice);

			result = pstmt.executeUpdate(); // insert문 : executeUpdate()
			conn.commit();	// 반드시 commit()
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();  
			}
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}

		return result;
	}//menuAdd

	// 메뉴 삭제
	public int delete (String menu) throws Exception {
		int result = -1;

		Connection conn = getConnection();// 연결 객체
		PreparedStatement pstmt = null;// SQL 해석 객체
		String sql = "delete coffeemenu where menu = ?";

		try {
			pstmt = conn.prepareStatement(sql); // SQL 해석
			pstmt.setString(1, menu);
			
			if(pstmt.executeUpdate()==1){
				JOptionPane.showMessageDialog(null, "삭제 완료");
			}else{
				JOptionPane.showMessageDialog(null, "삭제 오류");
			}

			result = pstmt.executeUpdate(); // delete문 : executeUpdate()
			conn.commit(); // 반드시 commit()
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return result;
	}

	// 결제 후 매출목록 추가
	public int coffeeadd(String payway, String menucode, String menu, int price, String ordertime){
		int result =-1;
		PreparedStatement pstmt =null;
		ResultSet rs = null;		

		try {
			conn = getConnection();
			String sql = "insert into coffee (payway, menucode, menu, price, ordertime) values (?, (select menucode from coffeemenu where menu=?), ?, ?, ?)";
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1,  payway);
			// select menucode from coffeemenu where menu=? 값에 menu를 넣어 menucode값 얻기
			pstmt.setString(2, menu);
			pstmt.setString(3, menu);
			pstmt.setInt(4, price);
			pstmt.setString(5, ordertime);

			result = pstmt.executeUpdate();
			conn.commit();	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();  
			}
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}

		return result;
	}//coffeeadd

	// 현금 판매한 매출의 합
	public Vector<Info> GetTotalCash() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		// price의 합계 구하기
		String sql = "select sum(price) from coffee where payway like '현금'";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info totalCash = new Info() ;
				totalCash.setTotalCash(rs.getInt("sum(price)"));					
				lists.add( totalCash ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee

	// 카드 판매 한 매출의 합
	public Vector<Info> GetTotalCard() {
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		// price의 합계 구하기
		String sql = "select sum(price) from coffee where payway like '카드'";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info totalCard = new Info() ;
				totalCard.setTotalCard(rs.getInt("sum(price)"));					
				lists.add( totalCard ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee

	// 메뉴코드가 H로 시작하는 메뉴 리스트 (hot coffee)
	public Vector<Info> GetHotCoffee() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select menu, price from coffeemenu where menucode like 'H%'";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info hot = new Info() ;
				hot.setMenu(rs.getString("menu"));	
				hot.setPrice(rs.getInt("price"));
				lists.add( hot ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetHotCoffee

	// 메뉴코드가 I로 시작하는 메뉴 리스트 (ice coffee)
	public Vector<Info> GetIceCoffee() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select menu, price from coffeemenu where menucode like 'I%'";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info ice = new Info() ;
				ice.setMenu(rs.getString("menu"));	
				ice.setPrice(rs.getInt("price"));
				lists.add( ice ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee

	// 메뉴코드가 B로 시작하는 메뉴 리스트 (beverage coffee)
	public Vector<Info> GetBeverageCoffee() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select menu, price from coffeemenu where menucode like 'B%'";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info beverage = new Info() ;
				beverage.setMenu(rs.getString("menu"));		
				beverage.setPrice(rs.getInt("price"));
				lists.add( beverage ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetBeverageCoffee

	// 메뉴별 판매량
	public  Vector<Info> Getsellcount(){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		// 메뉴 이름과 그 메뉴의 개수를 전체 행에서 파악
		// group by : 메뉴를 기준으로 그룹화 함 (by 판매개수)
		// order by :정렬기준
		String sql = "select menu , count(*)  from coffee group by menu order by count(*) desc" ;
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info coffee = new Info() ;
				coffee.setMenu (rs.getString("menu"));
				coffee.setPrice( rs.getInt("count(*)") ); 

				lists.add( coffee ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//Getsellcount

	// 매출리스트
	public Vector<Info> GetAllSellList() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from coffee" ;
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info info = new Info() ;
				info.setPayway(rs.getString("payway"));
				info.setMenuCode(rs.getString("menucode"));
				info.setMenu(rs.getString("menu"));
				info.setPrice( rs.getInt("price") ); 
				info.setDate(rs.getString("ordertime"));

				lists.add( info ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetAllSellList

	// 당일 카드매출
	public Vector<Info> currentCellCard() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select sum(price), SUBSTR(ordertime,1,13) from coffee where payway like '카드' group by SUBSTR(ordertime,1,13)";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info totalCard = new Info() ;
				totalCard.setTotalCard(rs.getInt("sum(price)"));	
				totalCard.setDate(rs.getString("SUBSTR(ordertime,1,13)"));
				lists.add( totalCard ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee

	// 당일 현금매출
	public Vector<Info> currentCellCash() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select sum(price), SUBSTR(ordertime,1,13) from coffee where payway like '현금' group by SUBSTR(ordertime,1,13)";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info totalCash = new Info() ;
				totalCash.setTotalCash(rs.getInt("sum(price)"));	
				totalCash.setDate(rs.getString("SUBSTR(ordertime,1,13)"));
				lists.add( totalCash ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee

	// 당일 전체 매출
	public Vector<Info> currentCellTotal() {//db에서 데이터를 받아서 벡터로 반환하는 메소드
		//모든 상품 목록들을 리턴한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select sum(price), SUBSTR(ordertime,1,13) from coffee group by SUBSTR(ordertime,1,13)";
		Vector<Info> lists = new Vector<Info>();
		try {
			conn = getConnection() ;
			pstmt = conn.prepareStatement(sql) ; 

			rs = pstmt.executeQuery() ;

			while(rs.next()){
				Info total = new Info() ;
				total.setPrice(rs.getInt("sum(price)"));	
				total.setDate(rs.getString("SUBSTR(ordertime,1,13)"));
				lists.add( total ) ;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return lists ;
	}//GetIceCoffee	

	//벡터를 받아서 매출리스트를 2차원 배열로 만들어주는 메소드
	public Object[][] makeArr(Vector<Info> lists){

		Object [][] coffeearr = new Object [lists.size()][5]; 			

		for(int i=0; i<lists.size();i++){
			coffeearr[i][0]=lists.get(i).getPayway();
			coffeearr[i][1]=lists.get(i).getMenuCode();
			coffeearr[i][2]=lists.get(i).getMenu();
			coffeearr[i][3]=lists.get(i).getPrice();
			coffeearr[i][4]=lists.get(i).getDate();
		}			

		return coffeearr;

	}//makeArr

	//벡터를 받아서 메뉴별 판대량을 2차원 배열로 만들어주는 메소드
	public Object[][] makelistArr(Vector<Info> lists){

		Object [][] coffeearr = new Object [lists.size()][2]; 

		for(int i=0; i<lists.size();i++){
			coffeearr[i][0]=lists.get(i).getMenu();
			coffeearr[i][1]=lists.get(i).getPrice();
		}		

		return coffeearr;

	}//makeArr

	

	
	
	
}