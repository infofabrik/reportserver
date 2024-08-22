package net.datenwerke.rs.theme.client.icon;

public enum BaseIconRotate {
   NORMAL(null), ROTATE_90("nf-fa-rotate_90"), ROTATE_180("nf-fa-rotate_180"), ROTATE_270("nf-fa-rotate_270"),
   FLIP_HORIZONTAL("nf-fa-flip_horizontal"), FLIP_VERTICAL("nf-fa-flip_vertical"),;

   private String style;

   BaseIconRotate(String style) {
      this.style = style;
   }

   public String toCssClass() {
      return style;
   }
}
