package com.c0.intellib.Spring;

public class Spring_session_management {
  /*
  <session-management invalid-session-url="/<exit-path-configuration>.html">
    <concurrency-control max-sessions="1" error-if-maximum-exceeded="true" />
</session-management>

Explanation(I answered wrong):

Spring Security’s session-management configuration handles both:
Single session per user
→ enforced by <concurrency-control max-sessions="1" …/>

Prevent concurrent logins (no new session if one exists)
→ controlled by error-if-maximum-exceeded="true"
(If set to false, it would expire the older session instead.)

Redirect on invalid session
→ provided by invalid-session-url="/<exit-path-configuration>.html"
   */
}
