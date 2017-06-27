package com.techelevator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DollarAmountTest {
	private DollarAmount dat;
	private DollarAmount dat1;
	private DollarAmount datneg;
	private String str;
	@Before
	public void setup(){
		dat = new DollarAmount(1000);
		dat1 = new DollarAmount(10, 0);
		datneg = new DollarAmount(-12);
		str = "1200";
	}
	
	@Test
	public void able_to_get_cents_and_dollars(){
		Assert.assertEquals(0, dat.getCents(),0.01);
		Assert.assertEquals(10, dat.getDollars());
	}
	
	@Test
	public void is_greater_than(){
		DollarAmount compare = new DollarAmount(500);
		boolean greater = dat.isGreaterThan(compare);
		
		Assert.assertTrue(greater);
	}
	
	@Test
	public void is_greater_than_does_not_allow(){
		DollarAmount compare = new DollarAmount(1500);
		boolean greater = dat.isGreaterThan(compare);
		
		Assert.assertFalse(greater);
	}
	
	@Test
	public void is_greater_than_or_equal_to(){
		DollarAmount compare = new DollarAmount(500);
		boolean greater = dat.isGreaterThanOrEqualTo(compare);
		
		Assert.assertTrue(greater);
	}
	
	@Test
	public void is_greater_than_or_equal_to_does_not_work(){
		DollarAmount compare = new DollarAmount(1500);
		boolean greater = dat.isGreaterThanOrEqualTo(compare);
		
		Assert.assertFalse(greater);
	}
	
	@Test
	public void is_less_than(){
		DollarAmount compare = new DollarAmount(1100);
		boolean less = dat.isLessThan(compare);
		
		Assert.assertTrue(less);
	}
	
	@Test
	public void is_less_than_does_not_work(){
		DollarAmount compare = new DollarAmount(500);
		boolean less = dat.isLessThan(compare);
		
		Assert.assertFalse(less);
	}
	
	@Test
	public void is_less_than_Equal(){
		DollarAmount compare = new DollarAmount(1000);
		boolean less = dat.isLessThanOrEqualTo(compare);
		
		Assert.assertTrue(less);
	}
	
	@Test
	public void is_less_than_or_equal_to_does_not_work(){
		DollarAmount compare = new DollarAmount(500);
		boolean less = dat.isLessThanOrEqualTo(compare);
		
		Assert.assertFalse(less);
	}
	
	@Test
	public void is_negative(){
		Assert.assertFalse(dat.isNegative());
	}
	
	@Test
	public void is_negative_part_2(){
		Assert.assertTrue(datneg.isNegative());
	}
	
	@Test
	public void minus(){
		
		Assert.assertEquals(0,dat1.minus(dat).getDollars());
	}
	
	@Test
	public void plus(){
		
		
		Assert.assertEquals(20, dat1.plus(dat).getDollars());
	}
	
	@Test
	public void divide(){
		
		
		Assert.assertEquals(0, dat1.divide(dat).getDollars());
	}
	
	@Test
	public void multiply(){
		
		
		Assert.assertEquals(10000, dat1.multiply(dat).getDollars());
	}
	
	@Test
	public void compare_to_greater_than(){
		DollarAmount compare = new DollarAmount(500);
		int compareTo = dat.compareTo(compare);
		
		Assert.assertEquals(1,compareTo);
	}
	
	@Test
	public void compare_to_less_than(){
		DollarAmount compare = new DollarAmount(1200);
		int compareTo = dat.compareTo(compare);
		
		Assert.assertEquals(-1,compareTo);
	}
	
	@Test
	public void compare_else(){
		DollarAmount compare = new DollarAmount(1000);
		int compareTo = dat.compareTo(compare);
		
		Assert.assertEquals(0,compareTo);
	}
	
	@Test
	public void equals_else(){
		boolean eq = dat.equals(1900);
		Assert.assertFalse(dat.equals(eq));
	}
	
	@Test
	public void equals_if(){
		DollarAmount ten = new DollarAmount(1000);
		DollarAmount ten1 = new DollarAmount(1000);
		Assert.assertTrue(ten.equals(ten1));
		
	}
	
	@Test
	public void equals_null() {
		DollarAmount amount = new DollarAmount(1000);
		Assert.assertFalse(amount.equals(null));
	}
	
	@Test
	public void not_equals() {
		DollarAmount ten = new DollarAmount(1000);
		DollarAmount five = new DollarAmount(500);
		Assert.assertFalse(ten.equals(five));
	}
	
	@Test
	public void hash_code(){
		Assert.assertEquals(1000,dat.hashCode());
	}
	
	
	@Test
	public void to_string(){
		DollarAmount str2 = new DollarAmount(1200);
		Assert.assertTrue(str2.toString().equals("$12.00"));
	}
	
	
}

