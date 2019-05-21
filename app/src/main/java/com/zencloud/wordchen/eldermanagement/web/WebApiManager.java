package com.zencloud.wordchen.eldermanagement.web;

import com.gizwits.energy.android.lib.web.base.BaseWebAPIManager;
import com.zencloud.wordchen.eldermanagement.bean.Order;
import com.zencloud.wordchen.eldermanagement.bean.Service;
import com.zencloud.wordchen.eldermanagement.bean.ShoppingCar;
import com.zencloud.wordchen.eldermanagement.bean.User;
import com.zencloud.wordchen.eldermanagement.common.MyConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

import java.io.File;
import java.util.List;

import static com.zencloud.wordchen.eldermanagement.BuildConfig.API_SERVER_URL;

public class WebApiManager extends BaseWebAPIManager {
	private static WebApiManager instance;
	private boolean isInit;

	private WebApiManager() {
		setWebLogDir(MyConstant.PATH + MyConstant.WEB_LOG_DIR);

	}

	static {
		instance = new WebApiManager();
	}

	public synchronized void init() {
		if (!isInit) {
			isInit = true;
		}
	}

	public static WebApiManager getInstance() {
		return instance;
	}

	/**
	 * 用户注册
	 *
	 * @param nickname
	 * @param password
	 * @param commonCallback
	 * @return
	 */
	public Callback.Cancelable register(String nickname, String password, Callback.CommonCallback<ApiResponse<Object>> commonCallback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.REGISTER);
		params.addQueryStringParameter(WebApi.Parameters.NICKNAME, nickname);
		params.addQueryStringParameter(WebApi.Parameters.PASSWORD, password);
		return xRequest(HttpMethod.POST, params, commonCallback);
	}

	/**
	 * 用户登录
	 *
	 * @param nickname
	 * @param password
	 * @param commonCallback
	 * @return
	 */
	public Callback.Cancelable login(String nickname, String password, Callback.CommonCallback<ApiResponse<User>> commonCallback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.LOGIN);
		params.addQueryStringParameter(WebApi.Parameters.NICKNAME, nickname);
		params.addQueryStringParameter(WebApi.Parameters.PASSWORD, password);
		return xRequest(HttpMethod.POST, params, commonCallback);
	}


	/**
	 * 通过类型来获取所有相关服务
	 *
	 * @param type
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable getServiceByType(String type, Callback.CommonCallback<ApiResponse<List<Service>>> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.SERVICE_GET_SERVICE_BY_TYPE);
		params.addQueryStringParameter("type", type);
		return xRequest(HttpMethod.POST, params, callback);
	}

	/**
	 * 获取推荐服务
	 *
	 * @param hot
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable getServiceByHot(String hot, Callback.CommonCallback<ApiResponse<List<Service>>> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.SERVICE_GET_SERVICE_BY_HOT + "/" + hot);
		return xRequest(HttpMethod.POST, params, callback);
	}

	/**
	 * 获取购物车
	 *
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable getShopCartByUserId(int userId, Callback.CommonCallback<ApiResponse<List<ShoppingCar>>> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.SHOPCART_GET_SHOP_CART_BY_USER_ID);
		params.addParameter("userId", userId);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 删除购物车
	 *
	 * @param shopcartId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable deleteShopCartService(int shopcartId, Callback.CommonCallback<ApiResponse> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.SHOPCART_DELETE_SHOP_CART_SERVICE);
		params.addParameter("shopcartId", shopcartId);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 更新头像
	 *
	 * @param file
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable updateImage(File file, int userId, Callback.CommonCallback<ApiResponse<String>> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.UPLOADPIC);
		params.addParameter("file", file);
		params.addParameter("userId", userId);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 更新用户信息
	 *
	 * @param address
	 * @param age
	 * @param phone
	 * @param sex
	 * @param nickname
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable updateInfo(String address, String age, String phone, String sex, String nickname, int userId, Callback
			.CommonCallback<ApiResponse> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.UPDATEZ_INFO);
		params.addParameter("userId", userId);
		params.addParameter("address", address);
		params.addParameter("age", age);
		params.addParameter("phone", phone);
		params.addParameter("sex", sex);
		params.addParameter("nickname", nickname);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 加入购物车
	 *
	 * @param serviceId
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable addShop(int serviceId, int userId, Callback.CommonCallback<ApiResponse> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.ADD_SHOP);
		params.addParameter("serviceId", serviceId);
		params.addParameter("userId", userId);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 订单查询
	 *
	 * @param status
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable getOrder(String status, int userId, Callback.CommonCallback<ApiResponse<List<Order>>> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.GETORDER);
		params.addParameter("status", status);
		params.addParameter("userId", userId);
		return xRequest(HttpMethod.POST, params, callback);
	}


	/**
	 * 获取订单
	 *
	 * @param serviceId
	 * @param userId
	 * @param callback
	 * @return
	 */
	public Callback.Cancelable addOrder(int serviceId, int userId, Callback.CommonCallback<ApiResponse> callback) {
		RequestParams params = new RequestParams(API_SERVER_URL + WebApi.POST.ADDORDER);
		params.addParameter("serviceId", serviceId);
		params.addParameter("userId", userId);
		return xRequest(HttpMethod.POST, params, callback);
	}

}
