package com.payulatam.tienda.front.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import com.payulatam.tienda.common.Token;

import com.payulatam.tienda.front.util.RestUtil;

public class PagoViewController extends GenericForwardComposer {


	// Combo para los valores posibles para el m�todo de pago
	private Combobox tokensCombobox;

	// 	// Flag para saber si se esta creando o modificando
	private boolean isEdit;

	/**
	 * M�todo que entrega la data de metodos de pago y los estrega a la vista
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		tokensCombobox.setModel(new ListModelList(RestUtil.getTokens().getTokens()));
		isEdit = false;
		
	}

	/**
	 * M�todo que recibe el evento onClick del bot�n Save
	 */
	public void onClick$saveButton() throws InterruptedException {
		if (!validarCamposVacios()) {
			

		} else {
			Messagebox.show(Labels.getLabel("app.error.camposvacion"), "Error", Messagebox.OK, Messagebox.ERROR);

		}
	}

	/**
	 * M�todo que recibe el evento onClick del bot�n Cancelar
	 */
	public void onClick$cancelButton() {
		reset();
		// Executions.getCurrent().sendRedirect("/menu.zul");
	}

	/**
	 * M�todo que limpia los campos
	 */
	public void reset() {
		// saveButton.setLabel(Labels.getLabel("app.boton.crear"));
		
		isEdit = false;

	}

	/**
	 * M�todo que valida que los campos no esten vacios
	 * 
	 * @return true si alguno de los campos se encuentra vacio
	 */
	public boolean validarCamposVacios() {

		return true;
	}

	/**
	 * M�todo que actualiza la informaci�n de las cuentas para ser mostrada en la
	 * tabla
	 */
	public void actualizarModelo() {


	}

}
