package de.christinecoenen.code.zapp.app.livestream.api.model;


import androidx.annotation.NonNull;

public enum Channel {
	DAS_ERSTE("das_erste"),
	BR_NORD("br_nord"),
	BR_SUED("br_sued"),
	HR("hr"),
	MDR_SACHSEN("mdr_sachsen"),
	MDR_SACHSEN_ANHALT("mdr_sachsen_anhalt"),
	MDR_THUERINGEN("mdr_thueringen"),
	NDR_HAMBURG("ndr_hh"),
	NDR_MECKLENBURG_VORPOMMERN("ndr_mv"),
	NDR_NIEDERSACHSEN("ndr_nds"),
	NDR_SCHLESWIG_HOLSTEIN("ndr_sh"),
	RBB_BERLIN("rbb_berlin"),
	RBB_BRANDENBURG("rbb_brandenburg"),
	RB("rb"),
	SR("sr"),
	SWR_BADEN_WUERTTEMBERG("swr_bw"),
	SWR_RHEINLAND_PFALZ("swr_rp"),
	WDR("wdr"),
	ARD_ALPHA("ard_alpha"),
	TAGESSCHAU24("tagesschau24"),
	ONE("one"),

	ARTE("arte"),

	ZDF("zdf"),
	DREISAT("dreisat"),
	KIKA("kika"),
	PHOENIX("phoenix"),
	ZDF_INFO("zdf_info"),
	ZDF_NEO("zdf_neo"),

	DEUTSCHE_WELLE("deutsche_welle"),
	DEUTSCHE_WELLE_PLUS("deutsche_welle_plus"),

	PARLAMENTSFERNSEHEN_1("parlamentsfernsehen_1"),
	PARLAMENTSFERNSEHEN_2("parlamentsfernsehen_2");

	public static Channel getById(String id) {
		for (Channel channel : Channel.values()) {
			if (channel.id.equals(id)) {
				return channel;
			}
		}
		throw new IllegalArgumentException();
	}

	private final String id;

	Channel(String id) {
		this.id = id;
	}

	@NonNull
	@Override
	public String toString() {
		return id;
	}
}
