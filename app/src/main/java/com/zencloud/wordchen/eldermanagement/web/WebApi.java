package com.zencloud.wordchen.eldermanagement.web;

import com.gizwits.energy.android.lib.base.AbstractConstantClass;
import com.gizwits.energy.android.lib.web.base.BaseWebAPI;

public class WebApi extends BaseWebAPI {



	public final static class Parameters extends BaseParameters {

		public static final String NICKNAME = "nickname";
        public static final String PASSWORD = "password";

	}
	public final static class GET extends AbstractConstantClass {
	}
	public final static class POST extends AbstractConstantClass {
		public static final String REGISTER = "/user/register";
		public static final String LOGIN = "/user/login";
		public static final String SERVICE_GET_SERVICE_BY_TYPE = "/service/getServiceByType";
		public static final String SERVICE_GET_SERVICE_BY_HOT = "/service/getServiceByHot";
		public static final String SHOPCART_GET_SHOP_CART_BY_USER_ID = "/shopCart/getShopCartByUserId";
		public static final String SHOPCART_DELETE_SHOP_CART_SERVICE = "/shopCart/deleteShopCartService";
		public static final String UPLOADPIC = "/user/uploadImg";
		public static final String UPDATEZ_INFO = "/user/updateInfo";
		public static final String ADD_SHOP = "/shopCart/addShopCart";
		public static final String GETORDER = "/order/getStatusOrder";
		public static final String ADDORDER = "/order/requestOrder";

	}
}
