package net.datenwerke.rs.markdown.service.markdown.dot.internal;

import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.commonmark.node.Block;
import org.commonmark.parser.SourceLine;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParserFactory;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

import net.datenwerke.rs.markdown.service.markdown.dot.DotBlock;

public class DotBlockParser extends AbstractBlockParser {
   
   private static final Set<String> SUPPORTED_ATTRIBUTES = Stream.of("width", "height").collect(toSet());
   
   private final DotBlock block = new DotBlock();
   
   private String firstLine;
   private StringBuilder otherLines = new StringBuilder();

   @Override
   public Block getBlock() {
      return block;
   }

   @Override
   public void addLine(SourceLine line) {
      if (firstLine == null) {
         firstLine = line.getContent().toString();
     } else {
         otherLines.append(line.getContent());
         otherLines.append('\n');
     }
   }


   @Override
   public void closeBlock() {
      // first line contains attribute map
      Map<String, String> attributesMap = parseAttributes(firstLine.trim());
      Optional<Integer> width = attributesMap.containsKey("width")
            ? Optional.of(Integer.parseInt(attributesMap.get("width")))
            : Optional.empty();
      Optional<Integer> height = attributesMap.containsKey("height")
            ? Optional.of(Integer.parseInt(attributesMap.get("height")))
            : Optional.empty();
      
      block.setWidth(width);
      block.setHeight(height);
      block.setContent(otherLines.toString());
   }

   @Override
   public BlockContinue tryContinue(ParserState parserState) {
      SourceLine line = parserState.getLine();
      int index = parserState.getIndex();
      if (checkOpener(line, index)) {
         return BlockContinue.finished();
      }
      return BlockContinue.atIndex(index);
   }

   public static class Factory implements BlockParserFactory {
      @Override
      public BlockStart tryStart(ParserState state, MatchedBlockParser matchedBlockParser) {
         SourceLine line = state.getLine();
         int index = state.getIndex();
         if (checkOpener(line, index)) {
            return BlockStart.of(new DotBlockParser()).atIndex(index + 3);
         }
         return BlockStart.none();
      }
   }
   
   private static boolean checkOpener(SourceLine line, int index) {
      return line.getContent().length() - index >= 3 && line.substring(index, index + 3).getContent().equals("´´´");
   }
   
   private Map<String, String> parseAttributes(String content) {
      Map<String, String> attributesMap = new HashMap<>();
      String attributes = content.toString().trim();
      if (attributes.contentEquals(""))
         return attributesMap;
      for (String s : attributes.split("\\s+")) {
         String[] attribute = s.split("=");
         if (attribute.length > 1 && SUPPORTED_ATTRIBUTES.contains(attribute[0].toLowerCase())) {
            attributesMap.put(attribute[0], attribute[1]);
         } else {
            throw new IllegalArgumentException("'" + attribute[0] + "' attribute is not supported");
         }
      }
      return attributesMap;
   }
}