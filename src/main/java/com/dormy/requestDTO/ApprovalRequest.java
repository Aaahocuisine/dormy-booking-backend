package com.dormy.requestDTO;

import com.dormy.constant.Approvalstatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalRequest {
	
	private long id;
	private Approvalstatus status;

}
