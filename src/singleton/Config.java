package singleton;

public class Config{
    public String nome;
    private int qtdTentativas = 2;

    private Config(){}

    private static Config instance;

    public int getQtdTentativas(){
        return qtdTentativas;
    }

    public static Config getInstance(){
        if (instance == null)
            instance = new Config();
    
        return instance;
    }
}