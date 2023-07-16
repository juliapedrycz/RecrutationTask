package recrutation.witek.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

@Entity(name = "APICALLS")
public class ApiCalls {

    @Id
    private String LOGIN;
    @NotNull
    private Integer REQUEST_COUNT;

    public ApiCalls() {
    }

    public ApiCalls(String LOGIN) {
        this.LOGIN = LOGIN;
        this.REQUEST_COUNT = 1;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public Integer getREQUEST_COUNT() {
        return REQUEST_COUNT;
    }
}
