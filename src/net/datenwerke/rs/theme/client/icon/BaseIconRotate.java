package net.datenwerke.rs.theme.client.icon;

public enum BaseIconRotate {
   NORMAL(null), ROTATE_90("fa-rotate-90"), ROTATE_180("fa-rotate-180"), ROTATE_270("fa-rotate-270"),
   FLIP_HORIZONTAL("fa-flip-horizontal"), FLIP_VERTICAL("fa-flip-vertical"),;

   private String style;

   BaseIconRotate(String style) {
      this.style = style;
   }

   public String toCssClass() {
      return style;
   }
}
