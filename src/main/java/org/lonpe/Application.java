package org.lonpe;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.vertx.reactivex.sqlclient.Tuple;
import jakarta.inject.Inject;
import java.util.Map;
import org.lonpe.auth.PasswordEncoder;
import org.lonpe.lonrx.impl.UserLonServiceLon;
import org.lonpe.model.UserLon;
import org.lonpe.sql.CrudLon;

public class Application implements ApplicationEventListener<ServerStartupEvent> {

    @Inject
    CrudLon crudLon;

    @Inject
    UserLonServiceLon userLonServiceLon;

    @Inject
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        String m = ""
                + "          ███╗   ███╗ █████╗ ██████╗ ██╗ ██████╗             \n"
                + "          ████╗ ████║██╔══██╗██╔══██╗██║██╔═══██╗            \n"
                + "          ██╔████╔██║███████║██████╔╝██║██║   ██║            \n"
                + "          ██║╚██╔╝██║██╔══██║██╔══██╗██║██║   ██║            \n"
                + "          ██║ ╚═╝ ██║██║  ██║██║  ██║██║╚██████╔╝            \n"
                + "          ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝             \n"
                + "                                                              ";

        System.out.println(m);

        System.out.println("INICIANDO EN PUERTO " + event.getSource().getPort());

        Single<Map<String, Object>> one = crudLon.getOne("Select * from user_lon where username = 'admin'", Tuple.tuple());

        Single<Map<String, Object>> two = one.flatMap((Map<String, Object> t) -> {
            System.out.println("1) T es " + t);
            if (t.isEmpty()) {
                System.out.println("1a) Usuario Adm no existe. Se va a crear");
                UserLon u = doAdm();
                return userLonServiceLon.save(u);
            } else {
                System.out.println("1b) El usuario Adm si existe. ");
                return Single.just(t);
            }
        }).flatMap((Map<String, Object> t1) -> {

            System.out.println("2) El usuario adm es " + t1);

            String sql_departamentBaseTimePeriod = "CREATE OR REPLACE VIEW rest_dpbtp AS  "
                    + "SELECT dbtp.id as dbtp_id, ul.id as ul_id   "
                    + "FROM departament_base_time_period dbtp, base_time_period btp, departament d, base b, time_period tp, departament_user_lon dul, base_user_lon bul, user_lon ul  "
                    + "WHERE  "
                    + "dbtp.base_time_period_id=btp.id AND dbtp.departament_id = d.id  "
                    + "AND  "
                    + "btp.base_id = b.id AND btp.time_period_id = tp.id  "
                    + "AND  "
                    + "dul.user_lon_id = ul.id AND dul.departament_id = d.id  "
                    + "AND   "
                    + "bul.user_lon_id = ul.id AND bul.base_id = b.id  ";
            //       + "AND   "
            //         + "tp.type_lon = 'EDITION'";

            return crudLon.executeQry(sql_departamentBaseTimePeriod, Tuple.tuple());

        }).flatMap(new Function<Map<String, Object>, SingleSource<? extends Map<String, Object>>>() {
            @Override
            public SingleSource<? extends Map> apply(Map t2) throws Exception {
                System.out.println("2a ) El usuario adm es " + t2);

                String sql_dd = "CREATE OR REPLACE VIEW rest_dpbtp00 AS SELECT "
                        + "dbtp.id AS dbtp_id "
                        + "FROM departament_base_time_period dbtp, "
                        + "base_time_period btp, "
                        + "departament d, "
                        + "base b, "
                        + "time_period tp "
                        + "WHERE "
                        + "dbtp.base_time_period_id = btp.id "
                        + "AND dbtp.departament_id = d.id "
                        + "AND btp.base_id = b.id "
                        + "AND btp.time_period_id = tp.id ";
                //+ "AND tp.type_lon = 'EDITION' ";

                return crudLon.executeQry(sql_dd, Tuple.tuple());
            }
        });

        two.subscribe();

    }

    private UserLon doAdm() {
        final UserLon u = new UserLon();
        u.setPkey("ADM");
        u.setPname("ADM");
        u.setPassword(passwordEncoder.encode("1234"));
        u.setUsername("admin");
        // u.setAccountExpired(false);
        // u.setAccountLocked(false);
        u.setTypeLon("ADM");
        // u.setPasswordExpired(false);
        u.setEnabled(true);
        u.setEmail("mario.garcia.burgos@gmail.com");
        return u;
    }

}
