package discount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestDiscount {
    private DiscountItem discountItem1 = new DiscountItem();
    private DiscountItem discountItem2 = new DiscountItem();
    List<DiscountItem> discountItems = new ArrayList<DiscountItem>();
    private Item item = new Item();

    @Before
    public void setup() {
        discountItem1.setType("TENPERCENT")
                .setBarcodes(new String[]{"ITEM000000", "ITEM0000001"});

        discountItem2.setType("TWENTYPERCENT")
                .setBarcodes(new String[]{"ITEM000002", "ITEM0000003"});

        discountItems.add(discountItem1);
        discountItems.add(discountItem2);

        item.setBarcode("ITEM000001")
                .setName("可口可乐")
                .setUnit("瓶")
                .setCategory("食品")
                .setSubCategory("碳酸饮料")
                .setPrice(BigDecimal.valueOf(3.00));
    }

    @Test
    public void shouldGetDiscountedPriceWhenGivenAPrice() {
        BigDecimal ONE = BigDecimal.valueOf(1.0);
        BigDecimal price = BigDecimal.valueOf(6.0);
        assertEquals(BigDecimal.valueOf(5.4).multiply(ONE), Discount.TENPERCENT.discountResult(price));
        assertEquals(BigDecimal.valueOf(4.8).multiply(ONE), Discount.TWENTYPERCENT.discountResult(price));
    }

    @Test
    public void shouldGetTypeBaseOnBarcode () {
        String type = "";
        for (DiscountItem discountItem : discountItems) {
            String barcode = "ITEM000002";

            label:
            for (int i = 0; i < discountItem.getBarcodes().length; i++) {
                if (barcode == discountItem.getBarcodes()[i]){
                    type = discountItem.getType();
                    System.out.println("this is the right type:"+type);
                    break label;
                }
                type = discountItem.getBarcodes()[0];
            }
        }

        assertEquals("TWENTYPERCENT", type);
    }

}
