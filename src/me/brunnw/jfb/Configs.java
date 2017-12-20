package me.brunnw.jfb;

public class Configs implements Setup {

	public void onPluginEnable() {

		Main.jp.saveDefaultConfig();
		Main.jp.saveConfig();
		Main.nome_do_servidor = Main.jp.getConfig().getString("Informacoes.Nome-do-servidor");
		
	}

}
