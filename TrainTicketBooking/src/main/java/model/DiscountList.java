package model;

import java.util.HashMap;
import java.util.Map;

public class DiscountList {
    private Map<String, Double> discounts;

    public DiscountList(){
        this.discounts = new HashMap<>();
        this.addDiscount("", 0.0);
    }

    public Map<String, Double> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Map<String, Double> discounts) {
        this.discounts = discounts;
    }

    public Double getDiscount(String name){
        return discounts.get(name);
    }

    public void addDiscount(String name, Double amount){
        discounts.put(name, amount);
    }
}
