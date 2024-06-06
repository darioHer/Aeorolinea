// Clase Aeropuerto
public class Aeropuerto {
    private String código;
    private String nombre;
    private String población;
    private String país;
    private int GMT;

    public Aeropuerto(String código, String nombre, String población, String país, int GMT) {
        this.código = código;
        this.nombre = nombre;
        this.población = población;
        this.país = país;
        this.GMT = GMT;
    }

    public String getCódigo() {
        return código;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoblación() {
        return población;
    }

    public String getPaís() {
        return país;
    }

    public int getGMT() {
        return GMT;
    }
}

// Clase Vuelo
public class Vuelo {
    private String origen;
    private String destino;
    private int plazas;
    private String duración;

    public Vuelo(String origen, String destino, int plazas, String duración) {
        this.origen = origen;
        this.destino = destino;
        this.plazas = plazas;
        this.duración = duración;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getPlazas() {
        return plazas;
    }

    public String getDuración() {
        return duración;
    }
}

// Clase Compania
public class Compania {
    private String nombre;

    public Compania(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase AeropuertoCompania
public class AeropuertoCompania extends Aeropuerto {
    private Compania compañia;

    public AeropuertoCompania(String código, String nombre, String población, String país, int GMT, Compania compañia) {
        super(código, nombre, población, país, GMT);
        this.compañia = compañia;
    }

    public Compania getCompañia() {
        return compañia;
    }
}

// Clase VueloAeropuertoCompania
public class VueloAeropuertoCompania extends Vuelo {
    private AeropuertoCompania aeropuertoCompania;

    public VueloAeropuertoCompania(String origen, String destino, int plazas, String duración, AeropuertoCompania aeropuertoCompania) {
        super(origen, destino, plazas, duración);
        this.aeropuertoCompania = aeropuertoCompania;
    }

    public AeropuertoCompania getAeropuertoCompania() {
        return aeropuertoCompania;
    }
}

// Clase Main
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Cargar datos de aeropuertos
        List<Aeropuerto> aeropuertos = new ArrayList<>();
        String[] aeropuertosData = {
            "BCN01;Barcelona - El Prat;Barcelona;España;1",
            "MAD01;Madrid - Barajas;Madrid;España;1",
            "PAR01;Paris - Orly;Paris;Francia;1",
            "LON01;London - Heathrow;Londres;Gran Bretaña;0",
            "SPA01;Sao Paulo - Guarulhos;Sao Paulo;Brasil;-3"
        };

        for (String data : aeropuertosData) {
            String[] parts = data.split(";");
            Aeropuerto aeropuerto = new Aeropuerto(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
            aeropuertos.add(aeropuerto);
        }

        // Cargar datos de compañías
        List<Compania> companias = new ArrayList<>();
        String[] companiasData = {
            "Iberia",
            "British Air",
            "Ryan Air",
            "Spanair"
        };

        for (String nombre : companiasData) {
            Compania compania = new Compania(nombre);
            companias.add(compania);
        }

        // Crear lista de AeropuertoCompania
        List<AeropuertoCompania> aeropuertosCompanias = new ArrayList<>();
        for (Aeropuerto aeropuerto : aeropuertos) {
            for (Compania compania : companias) {
                AeropuertoCompania aeropuertoCompania = new AeropuertoCompania(
                        aeropuerto.getCódigo(),
                        aeropuerto.getNombre(),
                        aeropuerto.getPoblación(),
                        aeropuerto.getPaís(),
                        aeropuerto.getGMT(),
                        compania);
                aeropuertosCompanias.add(aeropuertoCompania);
            }
        }

        // Cargar datos de vuelos
        List<Vuelo> vuelos = new ArrayList<>();
        String[] vuelosData = {
            "R;BCN01;MAD01;01:00;Iberia;Airbus;150;L-15:00 X-15:00 V-15:00 D-15:00;-",
            "R;MAD01;BCN01;01:00;Iberia;Airbus;150;L-18:00 J-20:00 S-20:00;-",
            "R;BCN01;LON01;01:05;British Air;Boeing;180;L-10:00 J-10:00;-",
            "R;LON01;BCN01;01:05;British Air;Boeing;180;L-15:00 J-15:00;-",
            "R;BCN01;LON01;01:10;Ryan Air;Airbus;120;L-10:00 J-10:00;-",
            "R;LON01;BCN01;01:10;Ryan Air;Airbus;120;J-15:10 J-15:10;-",
            "R;LON01;SPA01;07:00;British Air;Boeing;200;X-09:45 J-09:45 S-09:45;-",
            "R;SPA01;MAD01;07:00;British Air;Boeing;200;X-20:25 J-20:25 S-20:25;-",
            "C;BCN01;MAD01;01:00;Spanair;Airbus;150;Viajes Condor;1/2009/15 20:30",
            "C;BCN01;MAD01;01:00;Spanair;Airbus;150;Viajes Condor;1/2009/15 20:30",
            "C;MAD01;BCN01;01:00;Iberia;Airbus;150;Viajes Condor;1/2009/22 20:30",
            "C;BCN01;LON01;01:00;British Air;Boeing;180;Viaje Ilusion;2/2009/22 20:30",
            "C;LON01;BCN01;01:00;British Air;Boeing;180;Viaje Ilusion;3/2009/05 15:19",
            "C;LON01;BCN01;01:00;British Air;Boeing;180;Viaje Ilusion;3/2009/05 15:19",
            "C;BCN01;LON01;01:00;Ryan Air;Airbus;120;TravelPlan;1/2009/25 15:18",
            "C;SPA01;SPA01;01:00;Ryan Air;Airbus;120;TravelPlan;1/2009/31 22:20",
            "C;SIB01;SPA01;01:00;Ryan Air;Airbus;120;TravelPlan;1/2009/31 22:20"
        };

        for (String data : vuelosData) {
            String[] parts = data.split(";");
            String tipo = parts[0];
            String origen = parts[1];
            String destino = parts[2];
            String duración = parts[3];
            int plazas = Integer.parseInt(parts[6]);
            String compañíaNombre = parts[4];

            AeropuertoCompania aeropuertoCompania = null;
            for (AeropuertoCompania ac : aeropuertosCompanias) {
                if (ac.getCompañia().getNombre().equals(compañíaNombre) && ac.getCódigo().equals(origen)) {
                    aeropuertoCompania = ac;
                    break;
                }
            }

            Vuelo vuelo;
            if (tipo.equals("R")) {
                vuelo = new Vuelo(origen, destino, plazas, duración);
            } else {
                vuelo = new VueloAeropuertoCompania(origen, destino, plazas, duración, aeropuertoCompania);
            }

            vuelos.add(vuelo);
        }

        // Mostrar lista de vuelos de salida desde cada aeropuerto
        for (Aeropuerto aeropuerto : aeropuertos) {
            System.out.println("Vuelos de salida desde " + aeropuerto.getNombre());
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getOrigen().equals(aeropuerto.getCódigo())) {
                    System.out.println(vuelo.getOrigen() + " -> " + vuelo.getDestino() + " - " + vuelo.getDuración());
                }
            }
            System.out.println();
        }

        // Mostrar lista de vuelos de llegada a cada aeropuerto
        for (Aeropuerto aeropuerto : aeropuertos) {
            System.out.println("Vuelos de llegada a " + aeropuerto.getNombre());
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getDestino().equals(aeropuerto.getCódigo())) {
                    System.out.println(vuelo.getOrigen() + " -> " + vuelo.getDestino() + " - " + vuelo.getDuración());
                }
            }
            System.out.println();
        }
    }
}
