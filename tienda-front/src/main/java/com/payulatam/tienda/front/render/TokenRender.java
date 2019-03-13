package com.payulatam.tienda.front.render;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.payulatam.tienda.common.Token;



public class TokenRender implements RowRenderer  {
	
	
	/**
     * Método que se encarga de renderizar la data sobre la tabla de Clientes  
     */
	@Override
	public void render(Row row, Object data) throws Exception {

		row.appendChild(new Label(((Token) data).getName()));
		row.appendChild(new Label(((Token) data).getMaskedNumber()));
		row.appendChild(new Label(((Token) data).getPaymentMethod()));
		row.appendChild(new Label(((Token) data).getTokenId()));

		

	}

}
