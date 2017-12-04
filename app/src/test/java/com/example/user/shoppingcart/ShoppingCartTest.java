package com.example.user.shoppingcart;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 03/12/2017.
 */

public class ShoppingCartTest {
    Plunger plunger;
    Rope rope;
    SkiMask skiMask;
    Customer customer;
    Customer customerLoyal;

    @Before
    public void before() {
        plunger = new Plunger();
        rope = new Rope();
        skiMask = new SkiMask();
        customer = new Customer(false);
        customerLoyal = new Customer(true);
    }

    @Test
    public void shoppingCartStartsEmpty() {
        assertEquals(0, customer.getShoppingCart().size());
    }

    @Test
    public void canAddItem() {
        customer.addItemToCart(rope);
        assertEquals(1, customer.getShoppingCart().size());
    }

    @Test
    public void canRemoveItem() {
        customer.addItemToCart(rope);
        customer.removeItemFromCart(rope);
        assertEquals(0, customer.getShoppingCart().size());
    }

    @Test
    public void canClearBasket() {
        customer.addItemToCart(rope);
        customer.addItemToCart(skiMask);
        customer.emptyShoppingCart();
        assertEquals(0, customer.getShoppingCart().size());
    }

    @Test
    public void canCalculateShoppingCartTotalValue() {
        customer.addItemToCart(rope);
        customer.addItemToCart(skiMask);
        assertEquals(19, customer.getValueOfShoppingCart(), 0.1);
    }

    @Test
    public void canCalculateShoppingCartValueWithTwoForOne() {
        customer.addItemToCart(skiMask);
        customer.addItemToCart(skiMask);
        assertEquals(4, customer.getValueOfShoppingCart(), 0.1);
    }

    @Test
    public void tenPercentDiscountNotAppliedWhenUnderTwenty() {
        customer.addItemToCart(rope);
        customer.addItemToCart(skiMask);
        assertEquals(19, customer.getValueOfShoppingCart(), 0.1);
    }

    @Test
    public void tenPercentDiscountAppliedWhenOverTwenty() {
        customer.addItemToCart(rope);
        customer.addItemToCart(rope);
        customer.addItemToCart(plunger);
        assertEquals(22.5, customer.getValueOfShoppingCart(), 0.1);
    }

    @Test
    public void canApplyLoyaltyDiscount() {
        customerLoyal.addItemToCart(rope);
        customerLoyal.addItemToCart(rope);
        customerLoyal.addItemToCart(plunger);
        assertEquals(18, customerLoyal.getValueOfShoppingCart(), 0.1);
    }
}
