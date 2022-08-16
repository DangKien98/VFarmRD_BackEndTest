package com.example.vfarmrdbackend.controller.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.vfarmrdbackend.payload.tool.ToolRequest;
import com.example.vfarmrdbackend.payload.others.MessageResponse;
import com.example.vfarmrdbackend.service.tool.ToolService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tool", description = "The Tool's API")
@RestController
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/api")
public class ToolController {
    @Autowired
    ToolService toolService;

    @GetMapping("/tools")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> getAllTools() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    toolService.getAllTools());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }

    @GetMapping("/tools/toolcategories/{toolcategory_id}")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> getAllToolsWithToolCategory_id(@PathVariable("toolcategory_id") int toolcategory_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    toolService.getAllToolsWithToolCategory_id(toolcategory_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }

    @GetMapping("/tools/{tool_id}")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> getTool(@PathVariable("tool_id") int tool_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    toolService.getTool(tool_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }

    @PostMapping("/tools")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> createTool(@RequestBody ToolRequest request,
            @RequestHeader("Authorization") String jwt) {
        try {
            toolService.createTool(request, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse("Thành công", "Tạo công cụ mới thành công!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }

    @PutMapping("/tools/{tool_id}")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> updateTool(@PathVariable("tool_id") int tool_id, @RequestBody ToolRequest request,
            @RequestHeader("Authorization") String jwt) {
        try {
            toolService.updateTool(tool_id, request, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse("Thành công", "Chỉnh sửa công cụ thành công!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }

    @DeleteMapping("/tools/{tool_id}")
    @PreAuthorize("hasAuthority('staff') or hasAuthority('manager')")
    public ResponseEntity<?> deleteTool(@PathVariable("tool_id") int tool_id,
            @RequestHeader("Authorization") String jwt) {
        try {
            toolService.deleteTool(tool_id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse("Thành công", "Xóa công cụ thành công!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("Lỗi", "Hệ thống đã gặp sự cố!"));
        }
    }
}
