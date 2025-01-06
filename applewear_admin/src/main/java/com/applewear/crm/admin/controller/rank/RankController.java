package com.applewear.crm.admin.controller.rank;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applewear.crm.dto.rank.RankDTO;
import com.applewear.crm.service.rank.RankService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.google.gson.Gson;

@Controller
public class RankController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private RankService rankService;

	@GetMapping("/rank-setup")
	public String rankSetup(Model model, @RequestParam(name = "id", required = false) Long rankId) {

		RankDTO rankDTO = new RankDTO();

		if (CommonUtil.validLong(rankId)) {
			rankDTO = rankService.getRankById(rankId);
		}

		model.addAttribute("rankDTO", rankDTO);
		model.addAttribute("rankList", rankService.getAllRanks());
		model.addAttribute("active", MenuConstants.RANK_SETUP);
		return "rank-setup";

	}

	@PostMapping("/rank-setup")
	public String rankSetup(@ModelAttribute("rankDTO") RankDTO rankDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validateRankSetuup(rankDTO, result);

			if (!result.hasErrors()) {

				Long rankId = rankService.manageRank(rankDTO);

				if (CommonUtil.validLong(rankId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Rank is saved successfully.");
					return "redirect:/rank-setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Rank saving failed.");
				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Rank saving failed.");
			}

		} catch (Exception e) {
			LOG.error("Exception occurred while saving rank => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("rankDTO", rankDTO);
		model.addAttribute("active", MenuConstants.RANK_SETUP);
		model.addAttribute("rankList", rankService.getAllRanks());
		return "rank-setup";
	}

	@PostMapping("/deleteRankById.json")
	@ResponseBody
	public String deleteRankById(@RequestBody Long id) {

		String result = "Unable to delete rank.";

		try {

			boolean deleteResult = rankService.deleteRankById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "Unable to delete rank because it is already in use.";
			LOG.error(
					"Exception occurred while deleteing rank by id => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(result);

	}

	private void validateRankSetuup(RankDTO rankDTO, BindingResult result) {

		if (!CommonUtil.validString(rankDTO.getName())) {
			result.rejectValue("name", null, "Name is required.");
		}

	}

}
