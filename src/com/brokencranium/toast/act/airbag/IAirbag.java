package com.brokencranium.toast.act.airbag;

public interface IAirbag {

	void addListener(IAirBagListener listener);

	void deploy();

	void removeListener(IAirBagListener listener);

}