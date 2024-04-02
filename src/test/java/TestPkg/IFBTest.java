package TestPkg;

import org.testng.annotations.Test;

import BasePkg.IFBBase;
import Pagepkg.IFBPage;
import Utilities.ExcelUtil;

public class IFBTest extends IFBBase {
	@Test
	public void VerifyAllScenarios() throws Exception
	{
		IFBPage obj = new IFBPage(driver);
		obj.IFBVerifyLogo();
		obj.VerifyIFBTitle();
		obj.IFBVerifyHttpConnection();
		obj.pagesource();
		
		
		//path of the excel file - to read data
				String x1 = "D:\\IFBLogin.xlsx";
				//name of the sheet inside excel
				String Sheet = "Sheet1"; 
				int rowcount = ExcelUtil.getRowCount(x1,Sheet);
				
				for(int i=1;i<=rowcount;i++)
				{
					String Email=ExcelUtil.getCellValue(x1,Sheet,i,0);
					System.out.println("\nEmail = "+Email);
					
					String Password=ExcelUtil.getCellValue(x1,Sheet,i,1);
					System.out.println("\nPassword = "+Password);
		            
					obj.SetValuesFromExcel(Email,Password);
				
	              }
				obj.SignIn();
				obj.IFBHeaders();
				obj.Search();
				obj.Screensht();
				obj.scrolldown();
				obj.windowhandleYoutube();
				obj.windowhandleInstagram();
	}
}
