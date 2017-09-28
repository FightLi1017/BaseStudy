package lcx.lcxpermission;

import java.util.List;

/**
 * Created by lichenxi on 2017/9/28.
 */

public interface PermissionCallBack {
  void permissionSuccess(int requestcode);

  void permissionFailed(int requestcode, List<String> deniedPermissions);
}
