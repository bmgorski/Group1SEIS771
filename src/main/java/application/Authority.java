package application;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Authority extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 609122004756632736L;

	private String email;
	private String authority;

	public Authority() {
		this(null);
	}

	public Authority(Long id) {
		this.setId(id);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
