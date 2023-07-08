package vitfed.kata_spr_boot_security_pp_3_1_3.service;

import vitfed.kata_spr_boot_security_pp_3_1_3.models.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void addRole(Role role);
    Role getRoleByName(String name);

}
