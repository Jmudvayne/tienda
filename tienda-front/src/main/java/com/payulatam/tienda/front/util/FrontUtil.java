package com.payulatam.tienda.front.util;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;

import com.payulatam.tienda.common.DeviceInfo;

/**
 * Calse con utilidades varias para el front
 * @author jorge.riveros
 *
 */
public class FrontUtil {
	
	
	public DeviceInfo cargarDeviceInfo() {
		
		HttpSession session = (HttpSession)(Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		return new DeviceInfo(Executions.getCurrent().getRemoteAddr(), session.getId(), session.getId(), Executions.getCurrent().getUserAgent());
		
	}

}
