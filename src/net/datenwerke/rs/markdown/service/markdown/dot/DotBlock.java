package net.datenwerke.rs.markdown.service.markdown.dot;

import java.util.Optional;

import org.commonmark.node.CustomBlock;

public class DotBlock extends CustomBlock {

   private String content;
   
   private Optional<Integer> width = Optional.empty();
   private Optional<Integer> height = Optional.empty();

   public DotBlock() {
   }
   
   public DotBlock(String content) {
       this.content = content;
   }
   
   public void setContent(String content) {
      this.content = content;
   }
   
   public String getContent() {
       return content;
   }

   public Optional<Integer> getWidth() {
      return width;
   }

   public void setWidth(Optional<Integer> width) {
      this.width = width;
   }

   public Optional<Integer> getHeight() {
      return height;
   }

   public void setHeight(Optional<Integer> height) {
      this.height = height;
   }
}

