package Name;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Name {
	public static WebDriver driver;
	public static Calendar prev_notice=Calendar.getInstance();
	public static Calendar last_notice=Calendar.getInstance();
	public static WebElement temp=null;
	public static String notice_site=null;
	public static String notice_content=null;
	public static Robot robot;
	public static String[] name= {"DXR","XFOC","HILT","TTX","MSK","GOM","KAKI","DEX",
									"FC","PTO","IBY","Z502","BBC","DXG","SPO","HERA",
									"NET","KTT","SSC","SET","AMS","DAVP","AGO","GRAFT"};
	public static HashMap<String, String> map=new HashMap<String, String>();
	public Name() {
        super();
        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        //prev_notice.set(prev_notice.YEAR,2019);
        //last_notice.set(last_notice.SECOND, 0);
        
        //Driver SetUp
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 960));
        base_url = "url"; //url
        
        map.put("DXR", "DXRII-KRW");
        map.put("XFOC", "��XFOC-KRW");
        map.put("HILT", "HILT-KRW");
        map.put("TTX", "��TTX-KRW");
        map.put("MSK", "��MSK-KRW");
        map.put("GOM", "��GOM-KRW");
        map.put("KAKI", "��KAKI-KRW");
        map.put("DEX", "��DEX-KRW");
        map.put("FC", "��FC-KRW");
        map.put("PTO", "��PTO-KRW");
        map.put("IBY", "��IBY-KRW");
        map.put("Z502", "��Z502-KRW");
        map.put("BBC", "��BBC-KRW");
        map.put("DXG", "��DXG-KRW");
        map.put("SPO", "��SPO-KRW");
        map.put("HERA", "��HERA-KRW");
        map.put("NET", "��NET-KRW");
        map.put("KTT", "��KTT-KRW");
        map.put("SSC", "��SSC-KRW");
        map.put("AMS", "��AMS-KRW");
        map.put("SET", "��SET-KRW");
        map.put("DAVP", "��DAVP-KRW");
        map.put("AGO", "��AGO-KRW");
        map.put("GRAFT", "��GRAFT-KRW");

        
        
    }
	public static void main(String[] args) throws InterruptedException, AWTException {
			//robot=new Robot();	
			Random num=new Random();
			
			last_notice.setTime(prev_notice.getTime());//�ð�����
			
			int recrawl=0;
			Name A=new Name();
			
			A.login("id","pw"); //���̵� ���
			for(int i=0;true;i++)
			{
				//long CCC=System.currentTimeMillis();
				
				recrawl=A.crawl();
				if(recrawl==0)
					A.crawl();
				if(last_notice.compareTo(prev_notice)>0)
				{
					System.out.println("������ ����!!");
					A.copyAndsearch();
					prev_notice.setTime(last_notice.getTime());
				}else
					System.out.println("���� �����Դϴ�.");
				System.out.println("���ΰ�ħ �ð��� : "+Calendar.getInstance().getTime());
				Thread.sleep(600+num.nextInt(200));  //���� ���ΰ�ħ �ð�
				
				//long CCCC=System.currentTimeMillis();
				//System.out.println(((double)(CCCC-CCC))/1000+"�� �ɸ�");
			}		
			
	}
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "ũ�� ��ġ ";// ��ġ
    
    private String base_url;
    
    
 
    public int crawl() throws InterruptedException {
 
    	WebElement AA=null;
    	String str=null;
        try {
            //get page (= ���������� url�� �ּ�â�� ���� �� request �� �Ͱ� ����)
            driver.get(base_url);
            Thread.sleep(300);
            AA=driver.findElement(By.className("date"));
            System.out.println("����");
        } 
        catch (NoSuchElementException e) {
        	Thread.sleep(200);
        	AA=driver.findElement(By.className("date"));
        	System.out.println("����2");
		}
        catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("������ ����� �˶��� �����մϴ�.");
            System.exit(0);
        } finally {
        	if(AA.getText()!=null)
        	{
        		str=AA.getText();
        		String[] time=str.split(" |\\.|:");
        		last_notice.set(2020,Integer.valueOf(time[0])-1,Integer.valueOf(time[1]),Integer.valueOf(time[2]),Integer.valueOf(time[3]));
        		temp=driver.findElement(By.className("row"));
                notice_site=temp.findElement(By.tagName("a")).getAttribute("href");
                return 1;
            } else {
            	System.out.println( Calendar.getInstance().getTime()+" �� �ڷ� �޾ƿ��µ� ���� �־���");
            	return 0;
            }
        }
 
    }
    public void copyAndsearch() throws InterruptedException {
    	try {
            driver.get(notice_site);
            Thread.sleep(200);
            temp=driver.findElement(By.className("content"));
            notice_content=temp.getText();
            gotoTrade(notice_content); //ī�� â ���� !!
    	}catch (NoSuchElementException e) {
        	System.out.println("�������� -----------");
		}
        catch (Exception e) {
        	System.out.println("�������� ---33333333333-");
        } finally {
        	
        }
    }
    public void gotoTrade(String str)
    {
    	 for(int i=0;i<name.length;i++)
             if(str.contains(name[i]))
             {
                str="url"+map.get(name[i]);
                driver.get(str);
           	 	System.out.println("���ο� ������ ���� ���α׷��� ŵ�ϴ�.");
           	 	System.exit(0);
             }
    }
    public void login(String id,String pw) throws InterruptedException {
    	driver.get("loginurl");
    	driver.findElement(By.className("email")).sendKeys(id);
    	driver.findElement(By.className("password")).sendKeys(pw);
    	WebElement e=driver.findElement(By.className("login-button"));
    	e.click();
    	Thread.sleep(1500); //�α��� �α�ð� 
    }
    
}
