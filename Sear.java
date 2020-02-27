package Search;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.org.apache.bcel.internal.generic.LSTORE;
import com.sun.xml.internal.ws.api.ha.StickyFeature;



public class Sear {
	public static WebDriver driver;
	public static Calendar prev_notice=Calendar.getInstance();
	public static Calendar last_notice=Calendar.getInstance();
	public static WebElement temp=null;
	public static String notice_site=null;
	public static String notice_content=null;
	public static Robot robot;
	public Sear() {
        super();
        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        prev_notice.set(prev_notice.YEAR,2019);
        last_notice.set(last_notice.SECOND, 0);
        
        //Driver SetUp
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(400, 200));
        base_url = "�ּ�";
    }
	public static void main(String[] args) throws InterruptedException, AWTException {
			robot=new Robot();
			String[] category= {"DXR","XFOC","HILT","TTX","MSK",
			                   "GOM","KAKI","DEX","FC","PTO",
			                   "Z502","DXG","IBY","BBC","NET",
			                   "HERA","SPO","SSC","KTT","AMS",
			                   "SET","DAVP","GRAFT","AGO"};
			
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("XFOC", "��XFOC");
			
			String str="";
			
			for(int i=0;i<category.length;i++)
				if(str.contains(category[i]))
					System.out.println(category[i]);
			
			
			/*
			Random num=new Random();
			int recrawl=0;
			Sear A=new Sear();
			for(int i=0;true;i++)
			{
				recrawl=A.crawl();
				if(recrawl==0)
					A.crawl();
				if(last_notice.compareTo(prev_notice)>0)
				{
					System.out.println("������ ����!!");
					A.copyAndpaste();
					prev_notice.setTime(last_notice.getTime());
				}else
					System.out.println("���� �����Դϴ�.");
				System.out.println("���ΰ�ħ �ð��� : "+Calendar.getInstance().getTime());
				Thread.sleep(1300+num.nextInt(700));
			}	
			*/
	}
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\Users\\kimseungyeon\\Desktop\\selenium-java-3.141.59\\chromedriver.exe";
    
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
    public void copyAndpaste() throws InterruptedException {
    	try {
            driver.get(notice_site);
            Thread.sleep(300);
            temp=driver.findElement(By.className("content"));
            notice_content=temp.getText();
            notice_content=notice_content.replace("\n\n", "\n");
            notice_content=notice_content+"\n"+last_notice.get(last_notice.DAY_OF_MONTH)+"�� "
            		+last_notice.get(last_notice.HOUR_OF_DAY)+"�� "
            		+last_notice.get(last_notice.MINUTE)+"��  ����";
            xypaste(1432,978, notice_content); //ī�� â ���� !!
    	}catch (NoSuchElementException e) {
        	System.out.println("�������� -----------");
		}
        catch (Exception e) {
        	System.out.println("�������� ---33333333333-");
        } finally {
        	
        }
    }
    public void xypaste(int x,int y,String str)
    {
    	StringSelection stringselection=new StringSelection(str);
		Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(stringselection, null);
		robot.mouseMove(x,y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    
}
