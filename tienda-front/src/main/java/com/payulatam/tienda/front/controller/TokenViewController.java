package com.payulatam.tienda.front.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import com.payulatam.tienda.common.CreditCardData;
import com.payulatam.tienda.common.Token;
import com.payulatam.tienda.common.request.SolicitudToken;
import com.payulatam.tienda.common.response.ResponseToken;
import com.payulatam.tienda.front.render.TokenRender;
import com.payulatam.tienda.front.util.RestUtil;

public class TokenViewController extends GenericForwardComposer {

	// Campo de texto para el nombre de la tarjeta
	private Textbox nameCardTextbox;

	// Campo de texto para el numero de la tarjeta
	private Textbox numberCardTextbox;

	// Campo de texto para la fecha de expiración de la tarjeta
	private Textbox expirationDateTextbox;

	// Combo para los valores posibles para el método de pago
	private Combobox methodPaymentCombobox;

	// Tabla para los token ya registrados
	private Grid tokensGrid;

	// Flag para saber si se esta creando o modificando
	private boolean isEdit;

	/**
	 * Método que entrega la data de metodos de pago y los estrega a la vista
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		methodPaymentCombobox.setModel(new ListModelList(RestUtil.getMetodosPago().getMetodosPago()));
		isEdit = false;
		tokensGrid.setRowRenderer(new TokenRender());
		actualizarModelo();
		
	}

	/**
	 * Método que recibe el evento onClick del botón Save
	 */
	public void onClick$saveButton() throws InterruptedException {
		if (!validarCamposVacios()) {
			CreditCardData dataTarjeta = new CreditCardData( numberCardTextbox.getValue(),expirationDateTextbox.getValue(), null, methodPaymentCombobox.getValue());
 			SolicitudToken solicitud = new SolicitudToken(nameCardTextbox.getValue());		solicitud.setCardData(dataTarjeta);
 				

			ResponseToken respuesta = RestUtil.tokenizarTarjeta(solicitud);
			if (respuesta.getCodigo().equals("0")) {
				Messagebox.show(Labels.getLabel("app.token.exito"));
				actualizarModelo();
				reset();
			} else {
				Messagebox.show(respuesta.getMensaje(), "Error", Messagebox.OK, Messagebox.ERROR);
			}
			

		} else {
			Messagebox.show(Labels.getLabel("app.error.camposvacion"), "Error", Messagebox.OK, Messagebox.ERROR);

		}
	}

	/**
	 * Método que recibe el evento onClick del botón Cancelar
	 */
	public void onClick$cancelButton() {
		reset();		
	}

	/**
	 * Método que limpia los campos
	 */
	public void reset() {
		
		nameCardTextbox.setValue("");
		numberCardTextbox.setValue("");
		expirationDateTextbox.setValue("");
		methodPaymentCombobox.setValue("");
		isEdit = false;

	}

	/**
	 * Método que valida que los campos no esten vacios
	 * 
	 * @return true si alguno de los campos se encuentra vacio
	 */
	public boolean validarCamposVacios() {

		return nameCardTextbox.getValue().equals("") || numberCardTextbox.getValue().equals("")
				|| expirationDateTextbox.getValue().equals("") || methodPaymentCombobox.getValue().equals("");
	}

	/**
	 * Método que actualiza la información de las cuentas para ser mostrada en la
	 * tabla
	 */
	public void actualizarModelo() {

		tokensGrid.setModel(
				new ListModelList(RestUtil.getTokens().getTokens() != null ? RestUtil.getTokens().getTokens()
						: new ArrayList<Token>()));

	}

}
