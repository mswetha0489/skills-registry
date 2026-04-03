package com.metabyte.skillregistry.service;

import com.metabyte.skillregistry.dto.ResolveResponse;
import com.metabyte.skillregistry.dto.SkillRequest;
import com.metabyte.skillregistry.entity.Skill;
import com.metabyte.skillregistry.entity.SkillAlias;
import com.metabyte.skillregistry.entity.UnmappedSkill;
import com.metabyte.skillregistry.exception.AliasExistsException;
import com.metabyte.skillregistry.exception.SkillExistsException;
import com.metabyte.skillregistry.exception.SkillNotFoundException;
import com.metabyte.skillregistry.repository.SkillAliasRepository;
import com.metabyte.skillregistry.repository.SkillRepository;
import com.metabyte.skillregistry.repository.UnmappedSkillRepository;
import com.metabyte.skillregistry.util.NormalizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
    public class SkillService {

        @Autowired
        private SkillRepository skillRepo;

        @Autowired
        private SkillAliasRepository aliasRepo;

        @Autowired
        private UnmappedSkillRepository unmappedRepo;

        public Skill createSkill(SkillRequest request) {
            String normalized = NormalizationUtil.normalize(request.name);

            if (skillRepo.findByName(normalized).isPresent()) {
               throw new SkillExistsException("Skill already exists", normalized);
            }

            Skill skill = new Skill();
            skill.setName(normalized);
            skill.setCategory(request.category);

            return skillRepo.save(skill);
        }

        public void addAlias(Long skillId, String aliasInput) {
            String alias = NormalizationUtil.normalize(aliasInput);

            if (aliasRepo.findByAlias(alias).isPresent()) {
                Skill existing = aliasRepo.findByAlias(alias).get().getSkill();
                throw new AliasExistsException("Alias already exists", existing.getName());
            }

            Skill skill = skillRepo.findById(skillId)
                    .orElseThrow(() -> new SkillNotFoundException("Skill not found"));

            SkillAlias sa = new SkillAlias();
            sa.setAlias(alias);
            sa.setSkill(skill);

            aliasRepo.save(sa);
        }

        public ResolveResponse resolve(String input) {
            String normalized = NormalizationUtil.normalize(input);
            System.out.println("normalized input : "+normalized);

            ResolveResponse res = new ResolveResponse();
            res.input = input;

            Optional<Skill> skill = skillRepo.findByName(normalized);

            if (skill.isPresent()) {
                res.canonical = skill.get().getName();
                res.resolved = true;
                return res;
            }

            Optional<SkillAlias> alias = aliasRepo.findByAlias(normalized);

            if (alias.isPresent()) {
                res.canonical = alias.get().getSkill().getName();
                res.resolved = true;
                return res;
            }

            UnmappedSkill unmapped = new UnmappedSkill();
            unmapped.setInput(input);
            unmapped.setSource("resolve-api");
            unmappedRepo.save(unmapped);

            res.canonical = "unknown";
            res.resolved = false;
            return res;
        }
    }

